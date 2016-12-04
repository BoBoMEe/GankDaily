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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.bobomee.android.common.util.DayNightUtil;
import com.bobomee.android.common.util.ToastUtil;
import com.bobomee.android.common.util.UIUtil;
import com.bobomee.android.data.datastore.repo.Repository;
import com.bobomee.android.data.di.internal.HasComponent;
import com.bobomee.android.data.repo.GetRepos;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.domain.DomainConstants;
import com.bobomee.android.domain.bean.GankCategory;
import com.bobomee.android.domain.bean.Results;
import com.bobomee.android.domain.bean.UserEntity;
import com.bobomee.android.domain.interactor.DefaultSubscriber;
import com.bobomee.android.htttp.rx.Transformers;
import com.bobomee.android.myapplication.R;
import com.bobomee.android.myapplication.base.BaseActivity;
import com.bobomee.android.myapplication.base.ReposListPresenter;
import com.bobomee.android.myapplication.base.ReposListView;
import com.bobomee.android.myapplication.databinding.ActivityMainBinding;
import com.bobomee.android.myapplication.di.ReposComponent;
import com.bobomee.android.myapplication.model.ReposModel;
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
import rx.functions.Action1;

public class MainActivity extends BaseActivity<ReposListView, ReposListPresenter>
    implements NavigationView.OnNavigationItemSelectedListener, ReposListView,
    HasComponent<ReposComponent> {

  private static final String TAG = "MainActivity";

  private rx.functions.Action1<Void> mLoginAction = aVoid -> login();

  @Inject protected Repository mRepository;

  ReposComponent mInitialize;
  @Inject ReposListPresenter mReposListPresenter;

  @Inject GetRepos mGetRepos;
  private ActivityMainBinding mMainBinding;

  @Override public ReposListPresenter getPresenter() {
    return mReposListPresenter;
  }


  @Override protected void onCreate(Bundle savedInstanceState) {

    mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    mInitialize = ReposComponent.Init.initialize(this);
    if (null != mInitialize) mInitialize.inject(this);

    super.onCreate(savedInstanceState);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });

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
    RxView.clicks(mMainBinding.appBarMainLayout.contentLayout.btn5)
        .throttleFirst(DomainConstants.ON_CLICK_DURATION, TimeUnit.MILLISECONDS)
        .subscribe(mLoginAction);

    RxView.clicks(mMainBinding.appBarMainLayout.contentLayout.btn3)
        .throttleFirst(DomainConstants.ON_CLICK_DURATION, TimeUnit.MILLISECONDS)
        .subscribe(new Action1<Void>() {
          @Override public void call(Void _void) {
            showImage();
          }
        });

    RxView.clicks(mMainBinding.appBarMainLayout.contentLayout.btn4)
        .throttleFirst(DomainConstants.ON_CLICK_DURATION, TimeUnit.MILLISECONDS)
        .subscribe(new Action1<Void>() {
          @Override public void call(Void _void) {
            setBtn4();
          }
        });
  }

  private void login() {

    mReposListPresenter.initialize();
  }

  @Override public void userList(List<ReposModel> userModels) {
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

    Wrapper<GankCategory> gankCategoryWrapper = Wrapper.<GankCategory>builder("getGirlList",
        new Integer[] { DomainConstants.PAGE_SIZE, DomainConstants.FIRST_PAGE })
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

  public void setBtn4()

  {
    Wrapper<List<UserEntity>> userEntityList =
        Wrapper.<List<UserEntity>>builder("userEntityList", new String[] {}).build();

    Subscription subscribe = mRepository.request(userEntityList)
        .compose(Transformers.switchSchedulers())
        .subscribe(new DefaultSubscriber<List<UserEntity>>() {
          @Override public void onNext(List<UserEntity> _userEntities) {
            super.onNext(_userEntities);
            Log.e(TAG, "onNext: ---->" + _userEntities);
          }
        });

    addSubscription(subscribe);
  }

  @Override public ReposComponent getComponent() {
    return mInitialize;
  }
}
