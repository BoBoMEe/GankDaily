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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import com.bobomee.android.data.api.RestService;
import com.bobomee.android.data.datastore.repo.Repository;
import com.bobomee.android.data.di.core.ApplicationContext;
import com.bobomee.android.data.serializer.UserCache;
import com.bobomee.android.data.serializer.Wrapper;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link Repository}.
 */
@Singleton public class DataStoreFactory {

  private final UserCache userCache;
  private final Context context;

  @Inject
  public DataStoreFactory(@ApplicationContext Context _context, @NonNull UserCache userCache) {
    this.context = _context;
    this.userCache = userCache;
  }

  /**
   * Create {@link Repository} from a user id.
   */
  public <T> Repository create(Wrapper<T> _wrapper) {
    Repository dataStore;

    if (!isThereInternetConnection()) {
      if (cacheExpired(_wrapper)) {
        dataStore = new DiskDataStore(this.userCache);
      } else {
        dataStore = createCloudDataStore();
      }
    } else {
      if (!_wrapper.isRefresh() && cacheExpired(_wrapper)) {
        dataStore = new DiskDataStore(this.userCache);
      } else {
        dataStore = createCloudDataStore();
      }
    }

    return dataStore;
  }

  private <T> boolean cacheExpired(Wrapper<T> _wrapper) {
    return !this.userCache.isExpired() && this.userCache.isCached(_wrapper.getUnique());
  }

  /**
   * Create {@link Repository} to retrieve data from the Cloud.
   */
  public Repository createCloudDataStore() {

    return new CloudDataStore(RestService.INSTANCE.getRestApi(), this.userCache);
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
