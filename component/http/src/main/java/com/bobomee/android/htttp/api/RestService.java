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

package com.bobomee.android.htttp.api;

import com.bobomee.android.htttp.retrofit2.client.Retrofit2Client;

/**
 * Created on 16/10/2.下午6:43.
 *
 * @author bobomee.
 * @description
 */

public enum RestService {

  INSTANCE;

  private RestApi mRestApi;

  RestService() {
    mRestApi = Retrofit2Client.INSTANCE.getRetrofitBuilder().baseUrl(RestApi.API_GANK_URL)
        .build()
        .create(RestApi.class);
  }

  public RestApi getRestApi() {
    return mRestApi;
  }

}
