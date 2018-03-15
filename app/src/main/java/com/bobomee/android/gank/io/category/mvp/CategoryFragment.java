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

package com.bobomee.android.gank.io.category.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.bobomee.android.gank.io.base.BaseFragment;
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
public abstract class CategoryFragment<P extends CategoryContract.CategoryPresenter> extends BaseFragment
    implements CategoryContract.CategoryView<P> {

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

  @Override public P getPresenter() {
    return mCategoryPresenter;
  }
}
