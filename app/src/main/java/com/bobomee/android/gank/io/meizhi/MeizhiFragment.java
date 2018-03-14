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

package com.bobomee.android.gank.io.meizhi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.meizhi.adapter.MeizhiAdapter;
import com.bobomee.android.gank.io.meizhi.adapter.MeizhiItemViewBinder;
import com.bobomee.android.gank.io.meizhi.mvp.MeizhiContract;
import com.bobomee.android.gank.io.meizhi.service.DataService;
import com.bobomee.android.gank.io.mvp.category.CategoryFragment;
import com.bobomee.android.gank.io.util.FabUtil;
import com.bobomee.android.gank.io.widget.WrapperStaggeredGridLayoutManager;
import com.bobomee.android.htttp.bean.Results;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author BoBoMEe
 * @since 2017/6/21
 */
public class MeizhiFragment extends CategoryFragment<MeizhiContract.MeizhiPresenter> implements
    MeizhiContract.MeizhiView {
  private MeizhiAdapter mMeizhiAdapter;
  @BindView(R.id.recycler) RecyclerView mRecycler;
  @BindView(R.id.swipelayout) SwipeRefreshLayout mSwipelayout;
  FloatingActionButton mFab;

  public static MeizhiFragment newInstance() {
    Bundle args = new Bundle();
    MeizhiFragment fragment = new MeizhiFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void setDatas(List<Results> datas) {
    mIsRequested = true;
    mSwipelayout.setRefreshing(false);
    DataService.startService(mBaseActivity, datas);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setViews();
    setListeners();
  }

  private void setListeners() {
    mMeizhiAdapter = new MeizhiAdapter();
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
      mMeizhiAdapter.setItems(datas);
    }
  }
}
