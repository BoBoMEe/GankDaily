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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.bobomee.android.common.mvp.BaseContract;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.base.adapter.LoadMoreDelegate;
import com.bobomee.android.gank.io.base.adapter.SwipeRefreshDelegate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author BoBoMEe
 * @since 2018/3/19
 */

public abstract class BaseRecyclerFragment<P extends BaseContract.IMvpPresenter>
    extends MvpFragment<P>
    implements SwipeRefreshDelegate.OnSwipeRefreshListener, LoadMoreDelegate.LoadMoreSubject {

  @BindView(android.R.id.list) public RecyclerView mRecycler;
  @BindView(R.id.swipe_refresh_layout) public SwipeRefreshLayout mSwipelayout;

  private SwipeRefreshDelegate refreshDelegate;
  private LoadMoreDelegate loadMoreDelegate;

  private AtomicInteger loadingCount;
  private boolean isEnd;
  protected boolean isClear = true;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    loadingCount = new AtomicInteger(0);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    refreshDelegate = new SwipeRefreshDelegate(mSwipelayout, this);
    loadMoreDelegate = new LoadMoreDelegate(this);
    loadMoreDelegate.attach(mRecycler);
    refreshDelegate.attach(view);
  }

  protected void loadData(boolean clear) {
    isClear = clear;
  }

  protected boolean onInterceptLoadMore() {
    return false;
  }

  protected void setRefresh(boolean refresh) {
    refreshDelegate.setRefresh(refresh);
  }

  @Override public void onSwipeRefresh() {
    if (!onInterceptRefresh()) {
      loadData(true);
    }
  }

  protected boolean onInterceptRefresh() {
    return false;
  }

  @Override public View initFragmentView(LayoutInflater pInflater, ViewGroup pContainer,
      Bundle pSavedInstanceState) {
    return new View(mBaseActivity);
  }

  @Override public final void onLoadMore() {
    if (!isEnd()) {
      if (!onInterceptLoadMore()) {
        loadData(false);
      }
    }
  }

  protected boolean isShowingRefresh() {
    return refreshDelegate.isShowingRefresh();
  }

  public void setEnd(boolean end) {
    isEnd = end;
  }

  public boolean isEnd() {
    return isEnd;
  }

  protected void setSwipeToRefreshEnabled(boolean enable) {
    refreshDelegate.setEnabled(enable);
  }

  @Override public boolean isLoading() {
    return loadingCount.get() > 0;
  }

  protected void notifyLoadingStarted() {
    loadingCount.getAndIncrement();
  }

  protected void notifyLoadingFinished() {
    loadingCount.decrementAndGet();
  }
}
