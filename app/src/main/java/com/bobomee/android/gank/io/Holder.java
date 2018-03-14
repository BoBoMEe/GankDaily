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

package com.bobomee.android.gank.io;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;

/**
 * @author BoBoMEe
 * @since 2017/6/17
 */
public abstract class Holder extends ViewHolder {

  private SparseArray<View> mViews;
  private View mConvertView;

  public Holder(View itemView) {
    super(itemView);
    mConvertView = itemView;
    mViews = new SparseArray<View>();
  }

  public <T extends View> T getView(int viewId)
  {
    View view = mViews.get(viewId);
    if (view == null)
    {
      view = mConvertView.findViewById(viewId);
      mViews.put(viewId, view);
    }
    return (T) view;
  }

}
