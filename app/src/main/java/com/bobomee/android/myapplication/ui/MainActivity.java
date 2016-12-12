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
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import com.bobomee.android.common.util.DayNightUtil;
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
    implements NavigationView.OnNavigationItemSelectedListener, ReposListView {

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
            .setAction("Action", null).show());

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

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

    StaggeredGridLayoutManager staggeredGridLayoutManager =
        new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
    mMainBinding.appBarMainLayout.contentLayout.recycler.setLayoutManager(
        staggeredGridLayoutManager);

    mMainBinding.appBarMainLayout.contentLayout.recycler.setAdapter(mGankItemBeanCommonAdapter =
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
