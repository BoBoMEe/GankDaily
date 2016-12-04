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
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * Created on 2016/12/3.下午7:50.
 *
 * @author bobomee.
 * @description
 */
@Singleton public class ReposDiskDataRepository implements ReposRepository {

  private final UseCache mUserCache;

  @Inject public ReposDiskDataRepository(UseCache _userCache) {
    mUserCache = _userCache;
  }

  @Override public Observable<GankQuery> getQueryData(String query, String category, Integer count,
      Integer page) {

    Wrapper<GankQuery> getQueryData = this.<GankQuery>getWrapper("getQueryData", new Object[] {
        query, category, count, page
    });

    return mUserCache.<GankQuery>get(getQueryData);
  }

  @Override public Observable<GankDay> getGankData(Integer year, Integer month, Integer day) {

    Wrapper<GankDay> getGankData = this.<GankDay>getWrapper("getGankData", new Integer[] {
        year, month, day
    });

    return mUserCache.<GankDay>get(getGankData);
  }

  @Override
  public Observable<GankCategory> getCategoryData(String category, Integer count, Integer page) {

    Wrapper<GankCategory> getCategoryData =
        this.<GankCategory>getWrapper("getCategoryData", new Object[] {
            category, count, page
        });

    return mUserCache.<GankCategory>get(getCategoryData);
  }

  private <T> Wrapper<T> getWrapper(String method, Object[] params) {
    return Wrapper.<T>builder(method, params).build();
  }
}
