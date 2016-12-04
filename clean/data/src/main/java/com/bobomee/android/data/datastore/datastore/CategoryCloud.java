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

package com.bobomee.android.data.datastore.datastore;

import com.bobomee.android.data.datastore.DataStoreCloud;
import com.bobomee.android.data.serializer.UseCache;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.domain.bean.GankCategory;
import com.bobomee.android.htttp.api.RestApi;
import rx.Observable;

/**
 * Created on 2016/12/3.下午1:41.
 *
 * @author bobomee.
 * @description
 */

public class CategoryCloud extends DataStoreCloud<GankCategory> {
  public CategoryCloud(RestApi _restApi, UseCache _userCache) {
    super(_restApi, _userCache);
  }

  @Override public Observable<GankCategory> request(Wrapper<GankCategory> _wrapper) {

    setWrapper(_wrapper);

    Object[] params = _wrapper.getParams();
    String category = params[0].toString();
    Integer count = Integer.valueOf(params[1].toString());
    Integer page = Integer.valueOf(params[2].toString());

    Observable<GankCategory> mObservable = getRestApi().getCategoryData(category, count, page);

    mObservable.doOnNext(getAction1());

    return mObservable;
  }
}
