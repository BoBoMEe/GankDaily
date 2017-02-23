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

package com.bobomee.android.gank.io.util;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

/**
 * Project ID：400YF17050
 * Resume:     <br/>
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2017/2/23.汪波.
 */
public class FabUtil {

  public static void hideOrShow(RecyclerView pRecyclerView,
      FloatingActionButton pFloatingActionButton) {
    pRecyclerView.addOnScrollListener(new OnVerticalScrollListener(pFloatingActionButton));
  }

  private static class OnVerticalScrollListener extends RecyclerView.OnScrollListener {

    private final FloatingActionButton pFloatingActionButton;

    public OnVerticalScrollListener(FloatingActionButton pFloatingActionButton) {
      this.pFloatingActionButton = pFloatingActionButton;
    }

    @Override public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      if (!recyclerView.canScrollVertically(-1)) {
        onScrolledToTop();
      } else if (!recyclerView.canScrollVertically(1)) {
        onScrolledToBottom();
      } else if (dy < 0) {
        onScrolledUp();
      } else if (dy > 0) {
        onScrolledDown();
      }
    }

    public void onScrolledUp() {

    }

    public void onScrolledDown() {

    }

    public void onScrolledToTop() {
      pFloatingActionButton.hide();
    }

    public void onScrolledToBottom() {
      pFloatingActionButton.show();
    }
  }
}
