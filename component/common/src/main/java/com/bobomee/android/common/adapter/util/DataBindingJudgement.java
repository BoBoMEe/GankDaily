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

package com.bobomee.android.common.adapter.util;

import android.databinding.ObservableList;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.BaseAdapter;
import java.util.List;

/**
 * @author Kale
 * @date 2016/4/16
 */
public class DataBindingJudgement {

  public static final boolean SUPPORT_DATABINDING;

  static {
    boolean hasDependency;
    try {
      Class.forName("android.databinding.ObservableList");
      hasDependency = true;
    } catch (ClassNotFoundException e) {
      hasDependency = false;
    }

    SUPPORT_DATABINDING = hasDependency;
  }

  @SuppressWarnings("unchecked")
  public static void ensureDatabinding(@Nullable List data, BaseAdapter _adapter) {
    if (DataBindingJudgement.SUPPORT_DATABINDING && data instanceof ObservableList) {
      ((ObservableList) data).addOnListChangedCallback(
          new ObservableList.OnListChangedCallback<ObservableList>() {
            @Override public void onChanged(ObservableList sender) {
              _adapter.notifyDataSetChanged();
            }

            @Override public void onItemRangeChanged(ObservableList sender, int positionStart,
                int itemCount) {
              _adapter.notifyDataSetChanged();
            }

            @Override public void onItemRangeInserted(ObservableList sender, int positionStart,
                int itemCount) {
              _adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition,
                int itemCount) {
              _adapter.notifyDataSetChanged();
            }

            @Override public void onItemRangeRemoved(ObservableList sender, int positionStart,
                int itemCount) {
              _adapter.notifyDataSetChanged();
            }
          });
    }
  }

  @SuppressWarnings("unchecked")
  public static void ensureDatabinding(@Nullable List data, PagerAdapter _adapter) {
    if (DataBindingJudgement.SUPPORT_DATABINDING && data instanceof ObservableList) {
      ((ObservableList) data).addOnListChangedCallback(
          new ObservableList.OnListChangedCallback<ObservableList>() {
            @Override public void onChanged(ObservableList sender) {
              _adapter.notifyDataSetChanged();
            }

            @Override public void onItemRangeChanged(ObservableList sender, int positionStart,
                int itemCount) {
              _adapter.notifyDataSetChanged();
            }

            @Override public void onItemRangeInserted(ObservableList sender, int positionStart,
                int itemCount) {
              _adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition,
                int itemCount) {
              _adapter.notifyDataSetChanged();
            }

            @Override public void onItemRangeRemoved(ObservableList sender, int positionStart,
                int itemCount) {
              _adapter.notifyDataSetChanged();
            }
          });
    }
  }

  @SuppressWarnings("unchecked")
  public static void ensureDatabinding(@Nullable List data, RecyclerView.Adapter _adapter) {
    if (DataBindingJudgement.SUPPORT_DATABINDING && data instanceof ObservableList) {
      ((ObservableList) data).addOnListChangedCallback(
          new ObservableList.OnListChangedCallback<ObservableList>() {
            @Override public void onChanged(ObservableList sender) {
              _adapter.notifyDataSetChanged();
            }

            @Override public void onItemRangeChanged(ObservableList sender, int positionStart,
                int itemCount) {
              _adapter.notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override public void onItemRangeInserted(ObservableList sender, int positionStart,
                int itemCount) {
              _adapter.notifyItemRangeInserted(positionStart, itemCount);
              _adapter.notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override public void onItemRangeRemoved(ObservableList sender, int positionStart,
                int itemCount) {
              _adapter.notifyItemRangeRemoved(positionStart, itemCount);
              _adapter.notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition,
                int itemCount) {
              // Note:不支持一次性移动"多个"item的情况！！！！
              _adapter.notifyItemMoved(fromPosition, toPosition);
              _adapter.notifyDataSetChanged();
            }
          });
    }
  }
}
