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

import android.support.annotation.NonNull;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bobomee.android.common.adapter.CommonRcvAdapter;
import com.bobomee.android.common.adapter.interfaces.AdapterItem;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.util.GlideUtil;
import com.bobomee.android.gank.io.widget.ScaleImageView;
import com.bobomee.android.htttp.bean.Results;

/**
 * Project ID：400YF17050
 * Resume:     <br/>
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2017/2/23.汪波.
 */
public class MainAdapterProvider {

  public static MainAdapter provideAdapter() {
    return new MainAdapter();
  }

  public static class MainAdapter extends CommonRcvAdapter<Results> {

    public MainAdapter() {
      super();
    }

    @NonNull @Override public AdapterItem<Results> createItem(int type) {
      return new MainAdapterItem();
    }
  }

  static class MainAdapterItem implements AdapterItem<Results> {

    @BindView(R.id.image) ScaleImageView mImage;

    @Override public int getLayoutResId() {
      return R.layout.recycler_item_image;
    }

    @Override public void bindViews(View root) {
      ButterKnife.bind(this, root);
    }

    @Override public void setViews(Results pResults) {

    }

    @Override public void handleData(Results pResults, int position) {
      mImage.setInitSize(pResults.width, pResults.height);

      GlideUtil.load(mImage.getContext(), pResults.url, mImage);
    }
  }
}
