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

package com.bobomee.android.gank.io.category.meizhi.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.base.BaseRecyclerFragment;
import com.bobomee.android.gank.io.category.meizhi.DataLoadFinishEvent;
import com.bobomee.android.gank.io.category.meizhi.adapter.MeizhiAdapter;
import com.bobomee.android.gank.io.category.meizhi.adapter.MeizhiItemViewBinder;
import com.bobomee.android.gank.io.category.meizhi.di.CategoryComponent;
import com.bobomee.android.gank.io.category.meizhi.service.DataService;
import com.bobomee.android.gank.io.category.mvp.CategoryContract;
import com.bobomee.android.gank.io.util.FabUtil;
import com.bobomee.android.gank.io.widget.WrapperStaggeredGridLayoutManager;
import com.bobomee.android.htttp.bean.Results;
import java.util.List;
import javax.inject.Inject;
import me.drakeet.multitype.Items;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author BoBoMEe
 * @since 2017/6/21
 */
public class MeizhiFragment extends BaseRecyclerFragment<CategoryContract.ICategoryPresenter>
    implements CategoryContract.ICategoryView {
  private MeizhiAdapter mMeizhiAdapter;
  FloatingActionButton mFab;
  @Inject MeizhiPresenter mMeizhiListPresenter;
  private Items mItems;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    CategoryComponent.Init.INSTANCE.initialize(mBaseActivity, this).inject(this);
    setPresenter(mMeizhiListPresenter);
  }

  @Override protected void loadData(boolean clear) {
    mMeizhiListPresenter.subscribe(clear);
  }

  public static MeizhiFragment newInstance() {
    Bundle args = new Bundle();
    MeizhiFragment fragment = new MeizhiFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onResume() {
    mSwipelayout.setRefreshing(true);
    super.onResume();
    notifyLoadingStarted();
  }

  @Override public void setDatas(List<Results> datas) {
    mIsRequested = true;
    mSwipelayout.setRefreshing(false);
    DataService.startService(mBaseActivity, datas);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setHasOptionsMenu(true);
    setViews();
    setListeners();
  }

  private void setListeners() {
    mItems = new Items();
    mMeizhiAdapter = new MeizhiAdapter(mItems);
    mMeizhiAdapter.register(Results.class, new MeizhiItemViewBinder());
    mRecycler.setAdapter(mMeizhiAdapter);

    FabUtil.hideOrShow(mRecycler, mFab);
  }

  private void setViews() {
    mFab = (FloatingActionButton) mBaseActivity.findViewById(R.id.fab);

    mFab.setOnClickListener(v -> mRecycler.smoothScrollToPosition(0));

    WrapperStaggeredGridLayoutManager staggeredGridLayoutManager =
        new WrapperStaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
    mRecycler.setLayoutManager(staggeredGridLayoutManager);

    mSwipelayout.setOnRefreshListener(() -> {
      mMeizhiAdapter.clear();
      getPresenter().subscribe(true);
    });
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void dataEvent(DataLoadFinishEvent dataLoadFinishEvent) {
    List<Results> datas = dataLoadFinishEvent.getDatas();
    if (null != datas && !datas.isEmpty()) {

      Items tempItems = isClear ? new Items() : new Items(mItems);
      tempItems.addAll(datas);
      mItems = tempItems;

      mMeizhiAdapter.setItems(datas);
      mMeizhiAdapter.notifyDataSetChanged();
    }
    notifyLoadingFinished();
    setRefresh(false);
    setEnd(datas == null || datas.isEmpty());
  }

  @Override protected boolean onInterceptLoadMore() {
    if (!isLoading()) {
      mMeizhiListPresenter.addPage();
      loadData(false);
    }
    return true;
  }

  @Override public View initFragmentView(LayoutInflater pInflater, ViewGroup pContainer,
      Bundle pSavedInstanceState) {
    return pInflater.inflate(R.layout.content_meizhi, pContainer, false);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.meizhi_menu, menu);
  }

  @Override protected boolean isRegisterEventBus() {
    return true;
  }
}
