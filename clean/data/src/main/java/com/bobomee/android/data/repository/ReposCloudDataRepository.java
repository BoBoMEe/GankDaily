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

import com.bobomee.android.data.serializer.UseCache;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.domain.bean.GankCategory;
import com.bobomee.android.domain.bean.GankDay;
import com.bobomee.android.domain.bean.GankQuery;
import com.bobomee.android.htttp.api.RestApi;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Abner on 16/5/27.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
@Singleton public class ReposCloudDataRepository implements ReposRepository {

  @Inject RestApi mReposApi;

  @Inject UseCache mUserCache;

  @Inject public ReposCloudDataRepository() {
  }

  @Override public Observable<GankQuery> getQueryData(String query, String category, Integer count,
      Integer page) {
    return mReposApi.getQueryData(query, category, count, page)
        .doOnNext(storeAction("getQueryData", new Object[] {
            query, category, count, page
        }));
  }

  @Override public Observable<GankDay> getGankData(Integer year, Integer month, Integer day) {
    return mReposApi.getGankData(year, month, day)
        .doOnNext(storeAction("getGankData", new Integer[] {
            year, month, day
        }));
  }

  @Override
  public Observable<GankCategory> getCategoryData(String category, Integer count, Integer page) {
    return mReposApi.getCategoryData(category, count, page)
        .doOnNext(storeAction("getCategoryData", new Object[] {
            category, count, page
        }));
  }

  private <T> Action1<T> storeAction(String method, Object[] params) {
    return _t -> mUserCache.put(Wrapper.<T>builder(method, params).T(_t).build());
  }
}
