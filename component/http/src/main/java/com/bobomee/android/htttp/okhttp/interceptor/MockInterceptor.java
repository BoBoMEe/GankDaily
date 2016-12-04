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

package com.bobomee.android.htttp.okhttp.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created on 16/9/13.上午9:51.
 *
 * @author bobomee.
 * @description
 */
public class MockInterceptor implements Interceptor {

  @Override public Response intercept(Chain chain) throws IOException {

    /*Response response;
    String query = chain.request().url().uri().getQuery();
    int i = query.indexOf("&");
    if (i > 0) {
      String substring = query.substring(0, i);
      if (!TextUtils.isEmpty(substring) && substring.equals("information_favorite")) {
        String responseString =
            ImitateUtil.convertToString(UIUtil.getContext(), "favorite_news.json");

        if (responseString != null) {
          response = new Response.Builder().code(200)
              .message(responseString)
              .request(chain.request())
              .protocol(Protocol.HTTP_1_0)
              .body(ResponseBody.create(MediaType.parse("application/json"),
                  responseString.getBytes()))
              .addHeader("content-type", "application/json")
              .build();
        } else {
          response = chain.proceed(chain.request());
        }
      } else {
        response = chain.proceed(chain.request());
      }
    } else {
      response = chain.proceed(chain.request());
    }*/

    return chain.proceed(chain.request());
  }
}
