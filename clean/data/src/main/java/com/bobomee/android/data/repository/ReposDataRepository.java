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

package com.bobomee.android.data.repository;

import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.domain.bean.GankCategory;
import com.bobomee.android.domain.bean.GankDay;
import com.bobomee.android.domain.bean.GankQuery;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * Created on 2016/12/3.下午8:17.
 *
 * @author bobomee.
 * @description
 */
@Singleton public class ReposDataRepository implements ReposRepository {

  private final ReposDataStoreFactory mDataStoreFactory;

  @Inject public ReposDataRepository(ReposDataStoreFactory _dataStoreFactory) {
    mDataStoreFactory = _dataStoreFactory;
  }

  @Override public Observable<GankQuery> getQueryData(String query, String category, Integer count,
      Integer page) {
    Type type = new TypeToken<GankQuery>() {
    }.getType();
    ReposRepository reposRepository =
        this.<GankQuery>create(type, "getQueryData", new Object[] { query, category, count, page });

    return reposRepository.getQueryData(query, category, count, page);
  }

  @Override public Observable<GankDay> getGankData(Integer year, Integer month, Integer day) {
    Type type = new TypeToken<GankDay>() {
    }.getType();
    ReposRepository reposRepository =
        this.<GankDay>create(type, "getGankData", new Integer[] { year, month, day });

    return reposRepository.getGankData(year, month, day);
  }

  @Override
  public Observable<GankCategory> getCategoryData(String category, Integer count, Integer page) {
    Type type = new TypeToken<GankCategory>() {
    }.getType();

    ReposRepository reposRepository =
        this.<GankCategory>create(type, "getCategoryData", new Object[] { category, count, page });

    return reposRepository.getCategoryData(category, count, page);
  }

  private <T> ReposRepository create(Type _type, String method, Object[] params) {

    Wrapper<T> build = Wrapper.<T>builder(method, params).isRefresh(true).typeOfT(_type).build();

    return mDataStoreFactory.<T>create(build);
  }
}
