/*
 * Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.bobomee.android.gank.io.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created on 2016/12/12.下午1:08.
 *
 * @author bobomee.
 */

public class ScaleImageView extends android.support.v7.widget.AppCompatImageView {
  private int initWidth;
  private int initHeight;

  public ScaleImageView(Context context) {
    this(context, null);
  }

  public ScaleImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setInitSize(int initWidth, int initHeight) {
    this.initWidth = initWidth;
    this.initHeight = initHeight;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (initWidth > 0 && initHeight > 0) {
      int width = MeasureSpec.getSize(widthMeasureSpec);
      int height = MeasureSpec.getSize(heightMeasureSpec);

      float scale = (float) initHeight / (float) initWidth;
      if (width > 0){
        height = (int) ((float)width * scale);
      }
      setMeasuredDimension(width, height);
    } else {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
  }
}
