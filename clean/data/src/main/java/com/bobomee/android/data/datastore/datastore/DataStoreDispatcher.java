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
import com.bobomee.android.htttp.api.RestApi;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created on 2016/12/3.下午2:19.
 *
 * @author bobomee.
 * @description
 */

@Singleton
public class DataStoreDispatcher {

  private final RestApi restApi;
  private final UseCache userCache;
  private Wrapper wrapper;

  @Inject
  public DataStoreDispatcher(RestApi _restApi, UseCache _userCache) {
    restApi = _restApi;
    userCache = _userCache;
  }

  public DataStoreDispatcher setWrapper(Wrapper _wrapper) {
    wrapper = _wrapper;
    return this;
  }

  @SuppressWarnings("unchecked") public <T> DataStoreCloud<T> getDataStoreCloud() {
    String methodName = wrapper.getMethodName();

    DataStoreCloud dataStoreCloud = new DataStoreCloudDefault(restApi, userCache);

    switch (methodName) {
      case "getCategoryData":
        dataStoreCloud = new CategoryCloud(restApi, userCache);
        break;
      default:
        break;
    }

    return dataStoreCloud;
  }
}
