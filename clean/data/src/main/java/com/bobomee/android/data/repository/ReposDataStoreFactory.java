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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.bobomee.android.data.di.core.ApplicationContext;
import com.bobomee.android.data.serializer.UseCache;
import com.bobomee.android.data.serializer.Wrapper;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created on 2016/12/3.下午8:19.
 *
 * @author bobomee.
 * @description
 */

@Singleton public class ReposDataStoreFactory {

  private final UseCache userCache;
  private final Context context;

  @Inject ReposCloudDataRepository mReposCloudDataRepository;
  @Inject ReposDiskDataRepository mReposDiskDataRepository;

  @Inject public ReposDataStoreFactory(@ApplicationContext Context _context, UseCache userCache) {
    this.context = _context;
    this.userCache = userCache;
  }

  public <T> ReposRepository create(Wrapper<T> _wrapper) {
    ReposRepository reposRepository;

    if (!isThereInternetConnection()) {
      if (cacheExpired(_wrapper)) {
        reposRepository = mReposDiskDataRepository;
      } else {
        reposRepository = mReposCloudDataRepository;
      }
    } else {
      if (!_wrapper.isRefresh() && cacheExpired(_wrapper)) {
        reposRepository = mReposDiskDataRepository;
      } else {
        reposRepository = mReposCloudDataRepository;
      }
    }

    return reposRepository;
  }

  private <T> boolean cacheExpired(Wrapper<T> _wrapper) {
    return !this.userCache.isExpired() && this.userCache.isCached(_wrapper.getKey());
  }

  /**
   * Checks if the device has any active internet connection.
   *
   * @return true device with internet connection, otherwise false.
   */
  private boolean isThereInternetConnection() {
    boolean isConnected;

    ConnectivityManager connectivityManager =
        (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

    return isConnected;
  }
}
