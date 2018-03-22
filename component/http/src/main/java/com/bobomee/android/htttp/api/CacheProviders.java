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
import io.rx_cache.DynamicKey;
import io.rx_cache.DynamicKeyGroup;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created on 2016/12/6.下午10:53.
 *
 * @author bobomee.
 *         https://github.com/BoBoMEe
 */

public interface CacheProviders {

  Observable<Reply<GankQuery>> getQueryData(Observable<GankQuery> queryObservable,
      DynamicKeyGroup query, EvictDynamicKey queryUpdate);

  Observable<Reply<GankDay>> getGankDayData(Observable<GankDay> dayObservable, DynamicKey day,
      EvictDynamicKey dayUpdate);

  Observable<Reply<GankCategory>> getCategoryData(Observable<GankCategory> categoryObservable,
      DynamicKeyGroup day,EvictDynamicKey categoryUpdate);
}
