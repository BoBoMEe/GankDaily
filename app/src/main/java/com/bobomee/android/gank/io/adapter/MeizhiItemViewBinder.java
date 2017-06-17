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

package com.bobomee.android.gank.io.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.util.GlideUtil;
import com.bobomee.android.gank.io.widget.ScaleImageView;
import com.bobomee.android.htttp.bean.Results;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author BoBoMEe
 * @since 2017/6/17
 */
public class MeizhiItemViewBinder
    extends ItemViewBinder<Results, MeizhiItemViewBinder.ViewHolder> {

  @NonNull @Override protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.recycler_item_image, parent, false);
    return new ViewHolder(root);
  }

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Results meizhiItem) {
    holder.image.setInitSize(meizhiItem.width, meizhiItem.height);

    GlideUtil.load(holder.image.getContext(), meizhiItem.url, holder.image);
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @NonNull private final ScaleImageView image;
    ViewHolder(View itemView) {
      super(itemView);
      image = (ScaleImageView) itemView.findViewById(R.id.image);
    }
  }
}
