/*
 * Copyright (C) 2017.  BoBoMEe(wbwjx115@gmail.com)
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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bobomee.android.common.util.ToastUtil;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.base.BaseActivity;
import com.bobomee.android.gank.io.util.GlideUtil;
import com.bobomee.android.gank.io.util.ImageSaveUtil;
import com.bobomee.android.gank.io.util.ShareUtil;
import com.bobomee.android.htttp.bean.Results;
import java.io.File;
import rx.android.schedulers.AndroidSchedulers;
import uk.co.senab.photoview.PhotoView;

/**
 * Project ID：400YF17050
 * Resume:     <br/>
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2016/12/12.汪波.
 */
public class DetailImageActivity extends BaseActivity {
  @BindView(R.id.detail_image) PhotoView mDetailImage;
  public static final String RESULT = "RESULT";
  private Results mResults;
  private DetailImageActivity mDetailImageActivity;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.detail_main);
    ButterKnife.bind(this);
    mDetailImageActivity = this;

    invokeIntent();

    initView();
  }

  private void initView() {
    showToolBarBack();
    GlideUtil.load(this, mResults.url, mDetailImage);
  }

  private void invokeIntent() {
    mResults = getIntent().getParcelableExtra(RESULT);
  }

  public static void start(Context context, Results results) {
    Intent starter = new Intent(context, DetailImageActivity.class);
    starter.putExtra(RESULT, results);
    context.startActivity(starter);
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.meizhi_detail, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.save:

        addSubscription(
            ImageSaveUtil.saveImage(mDetailImageActivity, mResults.url, mResults.getDesc())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uri -> {
                  File appDir =
                      new File(Environment.getExternalStorageDirectory(), mResults.getDesc());
                  String msg = String.format(getString(R.string.picture_has_save_to),
                      appDir.getAbsolutePath());
                  ToastUtil.show(mDetailImageActivity, msg);
                }, error -> ToastUtil.show(mDetailImageActivity, error.getMessage() + "\n再试试...")));

        break;
      case R.id.share:
        addSubscription(
            ImageSaveUtil.saveImage(mDetailImageActivity, mResults.url, mResults.getDesc())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uri -> {
                  ShareUtil.shareImage(mDetailImageActivity, uri, mResults.getDesc());
                }));
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
