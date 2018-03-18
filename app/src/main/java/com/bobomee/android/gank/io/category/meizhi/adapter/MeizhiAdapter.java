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
import com.bobomee.android.gank.io.base.adapter.LoadMoreAdapter;
import java.util.List;
import me.drakeet.multitype.TypePool;

/**
 * @author BoBoMEe
 * @since 2017/6/17
 */
public class MeizhiAdapter extends LoadMoreAdapter {

  public MeizhiAdapter() {
    super();
  }

  public MeizhiAdapter(@NonNull List<?> items) {
    super(items);
  }

  public MeizhiAdapter(@NonNull List<?> items, int initialCapacity) {
    super(items, initialCapacity);
  }

  public MeizhiAdapter(@NonNull List<?> items, @NonNull TypePool pool) {
    super(items, pool);
  }

  public void clear() {
    List<?> items = getItems();
    if (!items.isEmpty()) {
      items.clear();
      notifyDataSetChanged();
    }
  }
}
