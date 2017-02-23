/*
 * Copyright (C) 2017.  BoBoMEe(wbwjx115@gmail.com)
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

package com.bobomee.android.gank.io.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.bobomee.android.common.util.DayNightUtil;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.base.BaseFragment;
import com.bobomee.android.gank.io.mvp.CategoryContract.ReposListPresenter;
import com.bobomee.android.gank.io.mvp.CategoryContract.ReposListView;
import com.bobomee.android.gank.io.service.DataService;
import com.bobomee.android.gank.io.ui.MainAdapterProvider.MainAdapter;
import com.bobomee.android.gank.io.util.FabUtil;
import com.bobomee.android.gank.io.widget.WrapperStaggeredGridLayoutManager;
import com.bobomee.android.htttp.bean.Results;
import com.bobomee.android.recyclerviewhelper.selectclick.click.ItemClick.OnItemClickListener;
import com.bobomee.android.recyclerviewhelper.selectclick.click.ItemClickSupport;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Project ID：400YF17050
 * Resume:    主页的界面<br/>
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2016/12/22.汪波.
 */
public class MainFragment extends BaseFragment
    implements ReposListView<Results, ReposListPresenter> {

  public static MainFragment newInstance() {
    Bundle args = new Bundle();
    MainFragment fragment = new MainFragment();
    fragment.setArguments(args);
    return fragment;
  }

  private ReposListPresenter mReposListPresenter;

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
    mReposListPresenter.subscribe(!mReposListPresenter.getRequsted());
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mReposListPresenter.unsubscribe();
  }

  private MainAdapter mGankItemBeanCommonAdapter;

  @Subscribe(threadMode = ThreadMode.MAIN) public void dataEvent(List<Results> data) {
    mGankItemBeanCommonAdapter.setData(data);
  }

  @BindView(R.id.recycler) RecyclerView mRecycler;
  @BindView(R.id.swipelayout) SwipeRefreshLayout mSwipelayout;

  @Override public void userList(List<Results> userModels) {
    if (!mReposListPresenter.getRequsted()) {
      mReposListPresenter.setRequested(true);
      mSwipelayout.setRefreshing(false);
      DataService.startService(mBaseActivity, userModels);
    }
  }

  @Override public Context context() {
    return mBaseActivity;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setHasOptionsMenu(true);

    FloatingActionButton fab = (FloatingActionButton) mBaseActivity.findViewById(R.id.fab);
    fab.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        mRecycler.smoothScrollToPosition(0);
      }
    });

    WrapperStaggeredGridLayoutManager staggeredGridLayoutManager =
        new WrapperStaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
    mRecycler.setLayoutManager(staggeredGridLayoutManager);

    mSwipelayout.setOnRefreshListener(new OnRefreshListener() {
      @Override public void onRefresh() {
        mReposListPresenter.setRequested(false);
        mGankItemBeanCommonAdapter.clearData();
        mReposListPresenter.subscribe(true);
      }
    });

    mRecycler.setAdapter(
        mGankItemBeanCommonAdapter = MainAdapterProvider.provideAdapter());

    ItemClickSupport lItemClickSupport = ItemClickSupport.from(mRecycler).add();
    lItemClickSupport.addOnItemClickListener(new OnItemClickListener() {
      @Override public void onItemClick(RecyclerView parent, View view, int position, long id) {
        mReposListPresenter.startDetail(mBaseActivity, mGankItemBeanCommonAdapter.getData().get(position));
      }
    });

    FabUtil.hideOrShow(mRecycler, fab);
  }

  @Override public void setPresenter(ReposListPresenter presenter) {
    this.mReposListPresenter = presenter;
  }

  @Override public View initFragmentView(LayoutInflater pInflater, ViewGroup pContainer,
      Bundle pSavedInstanceState) {
    return pInflater.inflate(R.layout.content_main, pContainer, false);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu, menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.night:
        DayNightUtil.switchDayNightMode(mBaseActivity);
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
