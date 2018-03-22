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

package com.bobomee.android.data;

import com.bobomee.android.common.util.UIUtil;
import com.bobomee.android.htttp.api.CacheProviders;
import com.bobomee.android.htttp.api.RestApi;
import com.bobomee.android.htttp.bean.GankCategory;
import com.bobomee.android.htttp.bean.GankDay;
import com.bobomee.android.htttp.bean.GankQuery;
import io.rx_cache.DynamicKey;
import io.rx_cache.DynamicKeyGroup;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * Created on 2016/12/6.下午11:08.
 *
 * @author bobomee.
 *         https://github.com/BoBoMEe
 */

@Singleton public class Repository {

  private RestApi mRestApi;
  private CacheProviders mCacheProviders;

  @Inject public Repository(RestApi restApi) {

    mCacheProviders =
        new RxCache.Builder().persistence(UIUtil.getContext().getFilesDir(), new GsonSpeaker())
            .using(CacheProviders.class);

    mRestApi = restApi;
  }

  public Observable<Reply<GankCategory>> getCategoryData(final String category, final Integer count,
      Integer page, final boolean update) {
    int currentDay = new Date().getDay();
    return mCacheProviders.getCategoryData(mRestApi.getCategoryData(category, count, page),
        new DynamicKeyGroup(currentDay, page), new EvictDynamicKey(update));
  }

  public Observable<Reply<GankDay>> getGankDayData(final Integer year, final Integer month,
      final Integer day, final boolean update) {
    return mCacheProviders.getGankDayData(mRestApi.getGankDayData(year, month, day),
        new DynamicKey(day), new EvictDynamicKey(update));
  }

  public Observable<Reply<GankQuery>> getQueryData(final String query, final String category,
      final Integer count, final Integer page, final boolean update) {
    return mCacheProviders.getQueryData(mRestApi.getQueryData(query, category, count, page),
        new DynamicKeyGroup(query + count, category + page), new EvictDynamicKey(update));
  }
}
