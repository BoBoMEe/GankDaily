/*
 *  Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.bobomee.android.gank.io.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bobomee.android.gank.io.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author drakeet
 */
public class CompactTabLayout extends TabLayout {

    private final int INDICATOR_HEIGHT;
    private int height;
    private int tabCount, lastTabCount;
    private float indicatorLeftOffset;
    private int selectedPosition;
    private Paint hoverPaint;
    private List<TextView> tabViews;
    private int[] tabWidths;


    public CompactTabLayout(Context context) {
        this(context, null);
    }


    public CompactTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    @SuppressLint("PrivateResource")
    public CompactTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabLayout,
            defStyleAttr, R.style.Widget_Design_TabLayout);
        int tabBackgroundResId = a.getResourceId(R.styleable.TabLayout_tabBackground, 0);
        a.recycle();
        INDICATOR_HEIGHT = dpToPx(2);
        hoverPaint = new Paint();
        hoverPaint.setColor(ContextCompat.getColor(context, tabBackgroundResId));
        hoverPaint.setStyle(Paint.Style.FILL);
        tabViews = new ArrayList<>(tabCount);
    }


    @Override public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(new HoverOnPageChangeListener(this));
        }
        assert viewPager != null;
        lastTabCount = tabCount;
        tabCount = viewPager.getAdapter().getCount();
        tabWidths = new int[tabCount];
    }


    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
    }


    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureTabWidths();
    }


    private void measureTabWidths() {
        for (int i = 0; i < tabCount; i++) {
            int tabWidth = ((LinearLayout) getChildAt(0)).getChildAt(i).getWidth();
            if (tabWidth == 0) {
                break;
            }
            tabWidths[i] = tabWidth;
        }
    }


    @Override protected void onFinishInflate() {
        super.onFinishInflate();
    }


    @Override protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (tabCount == 0) {
            return;
        }
        if (lastTabCount != tabCount) {
            measureTabWidths();
            lastTabCount = tabCount;
        }
        int offset = 0;
        for (int i = 0; i < selectedPosition; i++) {
            offset += tabWidths[i];
        }
        int tabWidth = tabWidths[selectedPosition];
        int indicatorWidth = tabWidth / 5;
        canvas.translate(offset, 0);    // 移动到第 N 个 tab 起点
        canvas.translate(indicatorLeftOffset * tabWidth, 0);    // 第 N 个 tab 当前滑动相对自身偏移量
        canvas.save();
        canvas.translate(0, height - INDICATOR_HEIGHT);     // 移动到底部下划线位置
        int halfHoverWidth = tabWidth / 2 - indicatorWidth / 2;
        canvas.drawRect(-2, 0, halfHoverWidth, INDICATOR_HEIGHT + 9, hoverPaint);
        canvas.translate((tabWidth / 2 + indicatorWidth / 2), 0);
        canvas.drawRect(0, 0, halfHoverWidth * 2, INDICATOR_HEIGHT + 9, hoverPaint);
        canvas.restore();
    }


    @Override
    public void addTab(@NonNull Tab tab, boolean setSelected) {
        TextView textView = (TextView) View.inflate(getContext(), R.layout.tab_text, null);
        textView.setText(tab.getText());
        /* Selected first one by default */
        if (tabViews.isEmpty()) {
            renderSelectedText(textView);
        }
        tab.setCustomView(textView);
        tabViews.add(textView);
        super.addTab(tab, setSelected);
    }


    public class HoverOnPageChangeListener extends TabLayoutOnPageChangeListener {

        HoverOnPageChangeListener(TabLayout tabLayout) {
            super(tabLayout);
        }


        @Override public void onPageSelected(int position) {
            super.onPageSelected(position);
            for (int i = 0; i < tabCount; i++) {
                final TextView textView = tabViews.get(i);
                if (i == position) {
                    renderSelectedText(textView);
                } else {
                    renderNormalText(textView);
                }
            }
        }


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            ViewCompat.postInvalidateOnAnimation(CompactTabLayout.this);
            indicatorLeftOffset = positionOffset;
            selectedPosition = position;
        }
    }


    private void renderSelectedText(TextView textView) {
        // textView.setTextSize(14);
        textView.setTypeface(null, Typeface.BOLD);
    }


    private void renderNormalText(TextView textView) {
        // textView.setTextSize(14);
        textView.setTypeface(null, Typeface.NORMAL);
    }


    int dpToPx(int dps) {
        return Math.round(getResources().getDisplayMetrics().density * dps);
    }
}
