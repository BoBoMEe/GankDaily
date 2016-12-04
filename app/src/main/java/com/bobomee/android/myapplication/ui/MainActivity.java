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
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.widget.ImageView;
import com.bobomee.android.common.util.DayNightUtil;
import com.bobomee.android.common.util.ToastUtil;
import com.bobomee.android.common.util.UIUtil;
import com.bobomee.android.data.datastore.repo.Repository;
import com.bobomee.android.data.di.internal.HasComponent;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.domain.DomainConstants;
import com.bobomee.android.domain.bean.GankCategory;
import com.bobomee.android.domain.bean.Results;
import com.bobomee.android.htttp.rx.Transformers;
import com.bobomee.android.myapplication.R;
import com.bobomee.android.myapplication.base.BaseActivity;
import com.bobomee.android.myapplication.databinding.ActivityMainBinding;
import com.bobomee.android.myapplication.di.ReposComponent;
import com.bobomee.android.myapplication.model.GankCategoryModel;
import com.bobomee.android.myapplication.mvp.presenter.CategoryListPresenter;
import com.bobomee.android.myapplication.mvp.view.ReposListView;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Subscription;

public class MainActivity extends BaseActivity<ReposListView, CategoryListPresenter>
    implements NavigationView.OnNavigationItemSelectedListener, ReposListView,
    HasComponent<ReposComponent> {

  private rx.functions.Action1<Void> mLoginAction = aVoid -> login();

  @Inject protected Repository mRepository;

  ReposComponent mInitialize;

  @Inject CategoryListPresenter mReposListPresenter;

  private ActivityMainBinding mMainBinding;

  @Override public CategoryListPresenter getPresenter() {
    return mReposListPresenter;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {

    mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    mInitialize = ReposComponent.Init.initialize(this);
    if (null != mInitialize) mInitialize.inject(this);

    super.onCreate(savedInstanceState);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(
        view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show());

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    initRecycler();
    initView();
  }

  private void initView() {
    RxView.clicks(mMainBinding.appBarMainLayout.contentLayout.showImage)
        .throttleFirst(DomainConstants.ON_CLICK_DURATION, TimeUnit.MILLISECONDS)
        .subscribe(_void -> {
          showImage();
        });

    RxView.clicks(mMainBinding.appBarMainLayout.contentLayout.showImageTost)
        .throttleFirst(DomainConstants.ON_CLICK_DURATION, TimeUnit.MILLISECONDS)
        .subscribe(mLoginAction);

  }

  private void login() {

    mReposListPresenter.initialize();
  }

  @Override public void userList(List<GankCategoryModel> userModels) {
    // TODO navigate to main page
    ToastUtil.show(this, Arrays.toString(userModels.toArray()));
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

      DayNightUtil.switchDayNightMode(this);
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public void initRecycler() {
    mMainBinding.appBarMainLayout.contentLayout.recycler.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

    mMainBinding.appBarMainLayout.contentLayout.recycler.setAdapter(mGankItemBeanCommonAdapter =
        new CommonAdapter<Results>(MainActivity.this, R.layout.recycler_item_image,
            mGankItemBeanList) {

          @Override protected void convert(ViewHolder holder, Results _gankItemBean, int position) {

            ImageView image = holder.getView(R.id.image);

            Glide.with(MainActivity.this).load(_gankItemBean.url).into(image);
          }
        });
  }

  private List<Results> mGankItemBeanList = new ArrayList<>();

  private CommonAdapter<Results> mGankItemBeanCommonAdapter;

  public void showImage() {

    Wrapper<GankCategory> gankCategoryWrapper = Wrapper.<GankCategory>builder("getCategoryData",
        new Object[] { DomainConstants.福利, DomainConstants.PAGE_SIZE, DomainConstants.FIRST_PAGE })
            .build();
    Subscription subscribe = mRepository.request(gankCategoryWrapper)
        .compose(Transformers.<GankCategory>switchSchedulers())
        .compose(Transformers.<List<Results>>handleGankResult())
        .subscribe(gankItemBeen -> {
          mGankItemBeanList.addAll(gankItemBeen);
          mGankItemBeanCommonAdapter.notifyDataSetChanged();
        }, throwable -> {
          UIUtil.showToastSafe("数据加载失败ヽ(≧Д≦)ノ");
        });
    addSubscription(subscribe);

  }

  @Override public ReposComponent getComponent() {
    return mInitialize;
  }
}
