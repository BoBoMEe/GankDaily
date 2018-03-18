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

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import butterknife.ButterKnife;

/**
 * @author drakeet
 */
public class SwipeRefreshDelegate {

  SwipeRefreshLayout swipeRefreshLayout;

  private OnSwipeRefreshListener providedListener;

  public interface OnSwipeRefreshListener {

    void onSwipeRefresh();
  }

  public SwipeRefreshDelegate(SwipeRefreshLayout swipeRefreshLayout,
      OnSwipeRefreshListener listener) {
    this.swipeRefreshLayout = swipeRefreshLayout;
    this.providedListener = listener;
  }

  public void attach(Activity activity) {
    ButterKnife.bind(this, activity);
    trySetupSwipeRefresh();
  }

  public void attach(View fragment) {
    ButterKnife.bind(this, fragment);
    trySetupSwipeRefresh();
  }

  private void trySetupSwipeRefresh() {
    if (swipeRefreshLayout != null) {
      swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override public void onRefresh() {
          providedListener.onSwipeRefresh();
        }
      });
    }
  }

  public void setRefresh(boolean requestDataRefresh) {
    if (swipeRefreshLayout == null) {
      return;
    }
    if (!requestDataRefresh) {
      swipeRefreshLayout.postDelayed(new Runnable() {
        @Override public void run() {
          if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
          }
        }
      }, 1000);
    } else {
      swipeRefreshLayout.setRefreshing(true);
    }
  }

  public void setEnabled(boolean enable) {
    if (swipeRefreshLayout == null) {
      throw new IllegalAccessError("The SwipeRefreshLayout has not been initialized.");
    }
    swipeRefreshLayout.setEnabled(enable);
  }

  public boolean isShowingRefresh() {
    return swipeRefreshLayout.isRefreshing();
  }

  public void setProgressViewOffset(boolean scale, int start, int end) {
    swipeRefreshLayout.setProgressViewOffset(scale, start, end);
  }
}
