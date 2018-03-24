/*
 *  Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.bobomee.android.gank.io.category.meizhi.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bobomee.android.gank.io.Holder;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.ui.DetailImageActivity;
import com.bobomee.android.gank.io.util.GlideUtil;
import com.bobomee.android.gank.io.widget.ScaleImageView;
import com.bobomee.android.htttp.bean.Results;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author BoBoMEe
 * @since 2017/6/17
 */
public class MeizhiItemViewBinder extends ItemViewBinder<Results, Holder> {

  @NonNull @Override
  protected Holder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return Holder.create(inflater, parent, R.layout.recycler_item_image);
  }

  @Override protected void onBindViewHolder(@NonNull Holder holder, @NonNull Results meizhiItem) {

    ScaleImageView view = holder.getView(R.id.image);
    view.setInitSize(meizhiItem.width, meizhiItem.height);
    GlideUtil.load(view.getContext(), meizhiItem.url, view);
    view.setOnClickListener(v -> DetailImageActivity.start(v.getContext(), meizhiItem));

    TextView publishedAt = holder.getView(R.id.publishedAt);
    publishedAt.setText(meizhiItem.publishedAt);
  }
}
