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

package com.bobomee.android.gank.io.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import com.bobomee.android.gank.io.widget.CircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

/**
 * Created on 2016/12/11.下午2:21.
 *
 * @author bobomee.
 *         https://github.com/BoBoMEe
 */

public class GlideUtil {

  public static void load(Context context, String url, ImageView iv) {
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(new ColorDrawable(Color.TRANSPARENT))
        .crossFade()
        .into(iv);
  }

  public static void load(Context context, String url, ImageView iv, int placeholder) {
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .crossFade()
        .placeholder(placeholder)
        .into(iv);
  }

  public static void load(Context context, int resId, ImageView iv) {
    Glide.with(context)
        .load(resId)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(new ColorDrawable(Color.TRANSPARENT))
        .crossFade()
        .into(iv);
  }

  /**
   * 需要在子线程执行
   */
  public static Bitmap load(Context context, String url) {
    try {
      return Glide.with(context)
          .load(url)
          .asBitmap()
          .diskCacheStrategy(DiskCacheStrategy.ALL)
          .placeholder(new ColorDrawable(Color.TRANSPARENT))
          .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
          .get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void loadCircle(Context context, int resId, ImageView iv) {
    Glide.with(context)
        .load(resId)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(new ColorDrawable(Color.TRANSPARENT))
        .crossFade()
        .transform(new CircleTransform(context))
        .into(iv);
  }
}
