/*
 *
 *  * Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package com.bobomee.android.gank.io.base.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bobomee.android.gank.io.R;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created BoBoMEe rc on 2018/1/30.
 * Description:
 */

public class LoadMoreViewBinder extends ItemViewBinder<LoadMore, LoadMoreViewBinder.ViewHolder> {
  private static final String TAG = "LoadMoreViewBinder";

  @NonNull @Override protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    View view = inflater.inflate(R.layout.item_load_more, parent, false);
    return new ViewHolder(view);
  }

  @Override protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LoadMore item) {

  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
      super(itemView);
    }
  }

  @Override protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
    super.onViewAttachedToWindow(holder);

    final ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
    if (layoutParams == null) {
      Log.e(TAG,
          " onViewAttacedToWindow layoutParams is a null object , Call setLayoutManager with a non-null argument.");
      return;
    }
    if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
      ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
    }
  }
}
