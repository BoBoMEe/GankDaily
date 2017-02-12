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

package com.bobomee.android.myapplication.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.bobomee.android.data.CacheRepository;
import com.bobomee.android.htttp.bean.Results;
import com.bobomee.android.myapplication.R;
import com.bobomee.android.myapplication.base.BaseActivity;
import com.bobomee.android.myapplication.databinding.ActivityMainBinding;
import com.bobomee.android.myapplication.mvp.presenter.CategoryListPresenter;
import com.bobomee.android.myapplication.mvp.view.ReposListView;
import com.bobomee.android.myapplication.service.DataService;
import com.bobomee.android.myapplication.util.GlideUtil;
import com.bobomee.android.myapplication.widget.ScaleImageView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity<ReposListView, CategoryListPresenter>
    implements ReposListView {

  @Inject CategoryListPresenter mReposListPresenter;

  private ActivityMainBinding mMainBinding;

  @Override public CategoryListPresenter getPresenter() {
    return mReposListPresenter;
  }

  @Inject CacheRepository mCacheRepository;

  private MainActivity mMainActivity;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    if (null != getComponent()) getComponent().inject(this);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(
        view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show());

    mMainActivity = this;
    EventBus.getDefault().register(this);

    initRecycler();
    initView();
  }

  private void initView() {

    mReposListPresenter.initialize(true);
  }

  @Override public void userList(List<Results> userModels) {
    // TODO navigate to main page
    //ToastUtil.show(this, Arrays.toString(userModels.toArray()));
    DataService.startService(mMainActivity, userModels);
  }

  //DayNightUtil.switchDayNightMode(this);

  public void initRecycler() {

    StaggeredGridLayoutManager staggeredGridLayoutManager =
        new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
    mMainBinding.contentLayout.recycler.setLayoutManager(staggeredGridLayoutManager);

    mMainBinding.contentLayout.recycler.setAdapter(mGankItemBeanCommonAdapter =
        new CommonAdapter<Results>(MainActivity.this, R.layout.recycler_item_image,
            mGankItemBeanList) {

          @Override protected void convert(ViewHolder holder, Results _gankItemBean, int position) {

            ScaleImageView image = holder.getView(R.id.image);
            image.setInitSize(_gankItemBean.width, _gankItemBean.height);

            GlideUtil.load(MainActivity.this, _gankItemBean.url, image);
          }
        });
  }

  private List<Results> mGankItemBeanList = new ArrayList<>();

  private CommonAdapter<Results> mGankItemBeanCommonAdapter;

  @Subscribe(threadMode = ThreadMode.MAIN) public void dataEvent(List<Results> data) {

    mGankItemBeanList.addAll(data);
    mGankItemBeanCommonAdapter.notifyDataSetChanged();
  }
}
