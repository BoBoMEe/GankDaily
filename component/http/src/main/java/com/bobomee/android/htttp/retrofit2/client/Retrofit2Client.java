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

package com.bobomee.android.htttp.retrofit2.client;

import com.bobomee.android.htttp.okhttp.client.okHttp;
import com.bobomee.android.htttp.retrofit2.converfactory.StringConverterFactory;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bobomee on 2016/5/19.
 *  @author bobomee.
 *         https://github.com/BoBoMEe
 */
@Singleton public class Retrofit2Client {

  private final Retrofit.Builder retrofitBuilder;

  @Inject
  Retrofit2Client(okHttp _okHttp) {
    retrofitBuilder = new Retrofit.Builder()
        //设置OKHttpClient
        .client(_okHttp.getOkHttpClient())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(StringConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create());
  }

  public Retrofit.Builder getRetrofitBuilder() {
    return retrofitBuilder;
  }

}
