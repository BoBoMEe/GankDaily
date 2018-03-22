/*
 *
 *  * Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package com.bobomee.android.gank.io.base;

import com.bobomee.android.common.mvp.BaseContract;

/**
 * Project ID：400YF17050
 * Resume:    主页的界面<br/>
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2016/12/22.汪波.
 */
public abstract class MvpFragment<P extends BaseContract.IMvpPresenter> extends BaseFragment
    implements BaseContract.IMvpView<P> {

  private P mCategoryPresenter;
  protected boolean mIsRequested = false;

  @Override public void onResume() {
    super.onResume();
    mCategoryPresenter.subscribe(!mIsRequested);
  }

  public void setRequested(boolean requested) {
    mIsRequested = requested;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mCategoryPresenter.unsubscribe();
  }

  @Override public void setPresenter(P presenter) {
    this.mCategoryPresenter = presenter;
  }

  @Override public P getPresenter() {
    return mCategoryPresenter;
  }
}
