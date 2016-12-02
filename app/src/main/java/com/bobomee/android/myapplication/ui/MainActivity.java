package com.bobomee.android.myapplication.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bobomee.android.common.util.UIUtil;
import com.bobomee.android.data.api.RestService;
import com.bobomee.android.data.bean.GankCategory;
import com.bobomee.android.data.bean.Results;
import com.bobomee.android.data.bean.UserEntity;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.htttp.rx.Transformers;
import com.bobomee.android.layout.Layout;
import com.bobomee.android.layout.LayoutBinder;
import com.bobomee.android.myapplication.R;
import com.bobomee.android.myapplication.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.bobomee.android.domain.DomainConstants;
import com.bobomee.android.domain.interactor.DefaultSubscriber;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.bobomee.android.common.util.DayNightUtil;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;

@Layout(R.layout.activity_main)
public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.btn_3) Button mBtn3;
  @BindView(R.id.recycler) RecyclerView mRecycler;

  @BindView(R.id.btn_4) Button mBtn4;

  private static final String TAG = "MainActivity";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LayoutBinder.bind(this);
    ButterKnife.bind(this);

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
    mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

    mRecycler.setAdapter(mGankItemBeanCommonAdapter =
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

  @OnClick(R.id.btn_3) public void showImage() {
    Subscription rxSubscription = RestService.INSTANCE.getRestApi()
        .getGirlList(DomainConstants.PAGE_SIZE, DomainConstants.FIRST_PAGE)
        .compose(Transformers.<GankCategory>switchSchedulers())
        .compose(Transformers.<List<Results>>handleGankResult())
        .subscribe(gankItemBeen -> {
          mGankItemBeanList.addAll(gankItemBeen);
          mGankItemBeanCommonAdapter.notifyDataSetChanged();
        }, throwable -> {
          UIUtil.showToastSafe("数据加载失败ヽ(≧Д≦)ノ");
        });
    addSubscription(rxSubscription);
  }

  @OnClick(R.id.btn_4) public void setBtn4()

  {
    //Wrapper<GankCategory> wrapper =
    //    Wrapper.<GankCategory>builder().method("getGirlList").params(new Object[] {
    //        DomainConstants.PAGE_SIZE, DomainConstants.FIRST_PAGE
    //    }).build();
    //
    //
    //Subscription subscribe = mRepository.request(wrapper)
    //    .compose(Transformers.switchSchedulers())
    //    .subscribe(new DefaultSubscriber<GankCategory>() {
    //      @Override public void onNext(GankCategory _gankCategory) {
    //        super.onNext(_gankCategory);
    //        Log.e(TAG, "onNext: ----->" + _gankCategory);
    //      }
    //    });

    Wrapper<List<UserEntity>> userEntityList =
        Wrapper.<List<UserEntity>>builder().method("userEntityList").build();

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
}
