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

package com.bobomee.android.gank.io.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Project ID：400YF17050
 * Resume:     <br/>
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2017/2/23.汪波.
 */
public class WrapperStaggeredGridLayoutManager extends StaggeredGridLayoutManager {
  public WrapperStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public WrapperStaggeredGridLayoutManager(int spanCount, int orientation) {
    super(spanCount, orientation);
  }

  public void onLayoutChildren(Recycler arg0, State arg1) {
    try {
      super.onLayoutChildren(arg0, arg1);
    } catch (Exception e) {
      Log.e("probe", "meet a IOOBE in RecyclerView");
    }
  }
}
