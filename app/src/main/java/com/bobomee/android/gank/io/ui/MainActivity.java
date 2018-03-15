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

package com.bobomee.android.gank.io.ui;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bobomee.android.common.util.ActivityUtils;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.base.BaseActivity;
import com.bobomee.android.gank.io.meizhi.mvp.MeizhiFragment;

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drawer_layout);
    ButterKnife.bind(this);

    showToolBarHome(R.drawable.ic_menu);

    MeizhiFragment meizhiFragment =
        (MeizhiFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

    if (null == meizhiFragment) {
      meizhiFragment = MeizhiFragment.newInstance();

      ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), meizhiFragment,
          R.id.contentFrame);
    }

    setNavigationView();
  }

  private void setNavigationView() {
    /**设置MenuItem的字体颜色**/
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    Resources resource = (Resources) getBaseContext().getResources();
    ColorStateList csl =
        (ColorStateList) resource.getColorStateList(R.color.navigation_menu_item_color);
    navigationView.setItemTextColor(csl);
    /**设置MenuItem默认选中项**/
    navigationView.getMenu().getItem(0).setChecked(true);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // Open the navigation drawer when the home icon is selected from the toolbar.
        mDrawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    return false;
  }
}
