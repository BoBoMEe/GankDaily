/*
 * Copyright (c) 2016. BoBoMEe(wbwjx115@gmail.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bobomee.android.data.datastore;

import android.annotation.TargetApi;
import android.os.Build;
import com.bobomee.android.htttp.api.RestApi;
import com.bobomee.android.data.datastore.repo.Repository;
import com.bobomee.android.data.serializer.UserCache;
import com.bobomee.android.data.serializer.Wrapper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import rx.Observable;
import rx.functions.Action1;

/**
 * {@link Repository} implementation based on connections to the api (Cloud).
 */
class CloudDataStore implements Repository {

  private final RestApi restApi;
  private final UserCache userCache;

  /**
   * Construct a {@link Repository} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  CloudDataStore(RestApi restApi, UserCache userCache) {
    this.restApi = restApi;
    this.userCache = userCache;
  }

  @SuppressWarnings("unchecked") @TargetApi(Build.VERSION_CODES.KITKAT) @Override
  public <T> Observable<T> request(final Wrapper<T> _wrapper) {

    Object[] params = _wrapper.getParams();
    Class<?>[] paramsType = getParamsType(params);
    String methodName = _wrapper.getMethodName();

    try {
      Method method = restApi.getClass().getMethod(methodName, paramsType);//反射获取方法
      Observable<T> observable = (Observable<T>) method.invoke(restApi, params);//发射调用方法

      return observable.doOnNext(new Action1<T>() {
        @Override public void call(T _t) {
          Wrapper build = _wrapper.getBuilder().T(_t).build();
          CloudDataStore.this.userCache.put(build);
        }
      });
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException _e) {
      _e.printStackTrace();
    }

    return Observable.empty();
  }

  private Class<?>[] getParamsType(Object[] _params) {

    if (null == _params || _params.length == 0) return null;

    int length = _params.length;

    Class<?>[] result = new Class<?>[length];

    for (int i = 0; i < length; i++) {

      result[i] = _params[i].getClass();
    }

    return result;
  }
}
