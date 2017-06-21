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

package com.bobomee.android.common.util;

import android.view.View;
import com.jakewharton.rxbinding.view.RxView;
import java.util.concurrent.TimeUnit;
import rx.Observable;

/**
 * @author BoBoMEe
 * @since 2017/6/17
 */
public class ClickUtils {

  /**
   * 在 1s 内 防止重复点击
   *
   * @param v 点击的控件
   * @return 点击的 Observable 流
   */

  public static synchronized Observable<Void> clicks(View v) {
    return clicks(v, 1000);
  }

  /**
   * 在 1s 内 防止重复点击
   *
   * @param v 点击的控件
   * @return 点击的 Observable 流
   */

  public static synchronized Observable<Void> longClicks(View v) {
    return clicks(v, 1500);
  }

  /**
   * 自定义 点击间隔 事件
   *
   * @param v 点击的控件
   * @param milliseconds 间隔事件
   * @return 点击的 Observable 流
   */
  public static synchronized Observable<Void> clicks(View v, long milliseconds) {
    return RxView.clicks(v)
        .throttleFirst(milliseconds, TimeUnit.MILLISECONDS)
        .compose(Transformers.mainSchedulers());
  }

  /**
   * 自定义 长点击间隔 事件
   *
   * @param v 点击的控件
   * @param milliseconds 间隔事件
   * @return 点击的 Observable 流
   */
  public static synchronized Observable<Void> longClicks(View v, long milliseconds) {
    return RxView.longClicks(v)
        .throttleFirst(milliseconds, TimeUnit.MILLISECONDS)
        .compose(Transformers.mainSchedulers());
  }
}
