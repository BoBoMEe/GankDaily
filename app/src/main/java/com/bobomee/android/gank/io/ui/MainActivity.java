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

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bobomee.android.common.util.ActivityUtils;
import com.bobomee.android.data.di.Dagger2Application;
import com.bobomee.android.data.di.internal.components.ApplicationComponent;
import com.bobomee.android.domain.DomainConstants;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.base.BaseActivity;
import com.bobomee.android.gank.io.di.category.CategoryComponent;
import com.bobomee.android.gank.io.di.category.CategoryModule;
import javax.inject.Inject;

public class MainActivity extends BaseActivity {

  @Inject MeizhiListPresenter mCategoryListPresenter;
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

    ApplicationComponent applicationComponent = Dagger2Application.get(this).getComponent();
    CategoryComponent.Init.INSTANCE.setApplicationComponent(
        applicationComponent);

    CategoryModule.Builder categoryModuleBuilder = CategoryModule.newBuilder();
    categoryModuleBuilder = categoryModuleBuilder.mMeizhiView(meizhiFragment);
    CategoryModule categoryModule = categoryModuleBuilder.build();

    CategoryComponent.Init.INSTANCE.setCategoryModule(categoryModule);

    CategoryComponent categoryComponent = CategoryComponent.Init.INSTANCE.initialize();
    categoryComponent.inject(this);

    mCategoryListPresenter.setParams(DomainConstants.福利, DomainConstants.PAGE_SIZE,
        DomainConstants.FIRST_PAGE);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // Open the navigation drawer when the home icon is selected from the toolbar.
        mDrawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }


}
