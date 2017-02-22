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

package com.bobomee.android.gank.io.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

/**
 * Created on 2016/12/14.下午5:10.
 *
 * @author bobomee.
 */

public class ImageAdapter {

  @BindingAdapter(value = {"android:src", "placeHolder"},
      requireAll = false) public static void setImageUrl(ImageView view, String url,int placeHolder) {

    DrawableTypeRequest<String> load = Glide.with(view.getContext()).load(url);

    if (placeHolder != 0){
      load.placeholder(placeHolder);
    }

    load.into(view);
  }
}
