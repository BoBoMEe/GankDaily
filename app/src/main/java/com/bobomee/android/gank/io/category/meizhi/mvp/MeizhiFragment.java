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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.base.BaseRecyclerFragment;
import com.bobomee.android.gank.io.category.meizhi.adapter.MeizhiAdapter;
import com.bobomee.android.gank.io.category.meizhi.adapter.MeizhiItemViewBinder;
import com.bobomee.android.gank.io.category.meizhi.di.CategoryComponent;
import com.bobomee.android.gank.io.category.mvp.CategoryContract;
import com.bobomee.android.gank.io.util.FabUtil;
import com.bobomee.android.htttp.bean.Results;
import java.util.List;
import javax.inject.Inject;
import me.drakeet.multitype.Items;

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
  }

  @Override protected void loadData(boolean clear) {
    super.loadData(clear);
    mMeizhiListPresenter.subscribe(clear);
  }

  public static MeizhiFragment newInstance() {
    Bundle args = new Bundle();
    MeizhiFragment fragment = new MeizhiFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void setDatas(List<Results> datas) {
    setRequested(true);
    dataEvent(datas);
    notifyLoadingFinished();
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

    mRecycler.setLayoutManager(new LinearLayoutManager(mBaseActivity) {
      @Override
      public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
          super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
          e.printStackTrace();
        }
      }
    });
  }

  public void dataEvent(List<Results> datas) {
    if (null != datas && !datas.isEmpty()) {

      Items tempItems = isClear ? new Items() : new Items(mItems);
      tempItems.addAll(datas);
      mItems = tempItems;

      mMeizhiAdapter.setItems(mItems);
      if (isClear) {
        mMeizhiAdapter.notifyDataSetChanged();
      } else {
        mMeizhiAdapter.notifyItemRangeInserted(mItems.size() - datas.size(), datas.size());
      }
    }
    setEnd(datas == null || datas.isEmpty());
    setRefresh(false);
  }

  @Override protected boolean onInterceptLoadMore() {
    if (!isLoading()) {
      mMeizhiListPresenter.addPage();
      loadData(false);
    }
    return true;
  }

  @Override protected boolean onInterceptRefresh() {
    if (!isLoading()) {
      mMeizhiListPresenter.resetPage();
      loadData(true);
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
}
