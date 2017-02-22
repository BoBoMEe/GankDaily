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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import butterknife.ButterKnife;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.base.BaseActivity;
import com.bobomee.android.gank.io.di.ReposComponent;
import com.bobomee.android.gank.io.mvp.presenter.CategoryListPresenter;
import com.bobomee.android.gank.io.util.ActivityUtils;
import javax.inject.Inject;

public class MainActivity extends BaseActivity {

  @Inject CategoryListPresenter mReposListPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(
        view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show());

    MainFragment lMainFragment =
        (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

    if (null == lMainFragment) {
      lMainFragment = MainFragment.newInstance();

      ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
          lMainFragment, R.id.contentFrame);
    }

    

    ReposComponent.Init.initialize(this, lMainFragment).inject(this);
  }
}
