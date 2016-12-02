package com.bobomee.android.data.api;

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
