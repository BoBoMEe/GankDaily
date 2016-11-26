/*
 * Copyright (c) 2016. BoBoMEe(wbwjx115@gmail.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bobomee.android.htttp.okhttp.client;

import com.bobomee.android.htttp.BuildConfig;
import com.bobomee.android.htttp.okhttp.interceptor.MockInterceptor;
import com.bobomee.android.htttp.okhttp.interceptor.OfflineCacheControlInterceptor;
import com.bobomee.android.htttp.okhttp.interceptor.UserAgentInterceptor;
import com.bobomee.android.htttp.util.CacheUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by bobomee on 16/5/19.
 */
public enum okHttp {

  INSTANCE;

  private final OkHttpClient okHttpClient;

  private static final int TIMEOUT_READ = 15;
  private static final int TIMEOUT_CONNECTION = 15;

  private Interceptor cacheInterceptor = new OfflineCacheControlInterceptor();

  okHttp() {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(/*message -> {
          if (BuildConfig.debug)
          Logger.t("OkHttp").d(message);
        }*/);
    interceptor.setLevel(
        BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);


    okHttpClient = new OkHttpClient.Builder()
        //打印请求log
        .addInterceptor(interceptor)

        //添加UA
        .addInterceptor(new UserAgentInterceptor(UserAgentInterceptor.UAHelper.INSTANCE.getUserAgent()))

        //添加mock
        .addInterceptor(new MockInterceptor())

        //stetho
        .addNetworkInterceptor(new StethoInterceptor())

        //走缓存
        .addInterceptor(cacheInterceptor)
        .addNetworkInterceptor(cacheInterceptor)

        //设置Cache目录
        .cache(CacheUtil.getCache())

        //失败重连
        .retryOnConnectionFailure(true)

        //time out
        .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)

        .build();
  }

  public OkHttpClient getOkHttpClient() {
    return okHttpClient;
  }

}
