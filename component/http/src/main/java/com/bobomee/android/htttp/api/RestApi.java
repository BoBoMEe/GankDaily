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

import com.bobomee.android.htttp.bean.GankCategory;
import com.bobomee.android.htttp.bean.GankDay;
import com.bobomee.android.htttp.bean.GankQuery;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
  String API_GANK_URL = "http://gank.io/api/";

  String API_URL_QUERY_DATA = "search/query/{query}/category/{category}/count/{count}/page/{page}";

  String API_URL_DAY_DATA = "day/{year}/{month}/{day}";

  String API_URL_CATEGORY_DATA = "data/{category}/{count}/{page}";

  @GET(API_URL_QUERY_DATA) Observable<GankQuery> getQueryData(@Path("query") String query,
      @Path("category") String category, @Path("count") Integer count, @Path("page") Integer page);

  @GET(API_URL_DAY_DATA) Observable<GankDay> getGankDayData(@Path("year") Integer year,
      @Path("month") Integer month, @Path("day") Integer day);

  @GET(API_URL_CATEGORY_DATA) Observable<GankCategory> getCategoryData(
      @Path("category") String category, @Path("count") Integer count, @Path("page") Integer page);
}
