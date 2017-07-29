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

package com.bobomee.android.gank.io.mvp.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.bobomee.android.common.util.DayNightUtil;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.base.BaseFragment;
import com.bobomee.android.gank.io.mvp.category.CategoryContract.CategoryPresenter;
import com.bobomee.android.gank.io.mvp.category.CategoryContract.CategoryView;
import org.greenrobot.eventbus.EventBus;

/**
 * Project ID：400YF17050
 * Resume:    主页的界面<br/>
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2016/12/22.汪波.
 */
public class CategoryFragment<P extends CategoryPresenter> extends BaseFragment
    implements CategoryView<P> {

  private P mCategoryPresenter;
  protected boolean mIsRequested = false;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventBus.getDefault().register(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  @Override public void onResume() {
    super.onResume();
    mCategoryPresenter.subscribe(!mIsRequested);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mCategoryPresenter.unsubscribe();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override public void setPresenter(P presenter) {
    this.mCategoryPresenter = presenter;
  }

  @Override public View initFragmentView(LayoutInflater pInflater, ViewGroup pContainer,
      Bundle pSavedInstanceState) {
    return pInflater.inflate(R.layout.content_main, pContainer, false);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.meizhi_menu, menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.night:
        DayNightUtil.switchDayNightMode(mBaseActivity);
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public P getPresenter() {
    return mCategoryPresenter;
  }
}
