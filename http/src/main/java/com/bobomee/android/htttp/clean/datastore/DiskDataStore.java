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
package com.bobomee.android.htttp.clean.datastore;

import com.bobomee.android.htttp.clean.datastore.repo.Repository;
import com.bobomee.android.htttp.clean.serializer.UserCache;
import com.bobomee.android.htttp.clean.serializer.Wrapper;
import rx.Observable;

/**
 * {@link Repository} implementation based on file system data store.
 */
class DiskDataStore implements Repository {

  private final UserCache userCache;

  /**
   * Construct a {@link Repository} based file system data store.
   *
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  DiskDataStore(UserCache userCache) {
    this.userCache = userCache;
  }

  @Override public <T> Observable<T> request(Wrapper<T> _wrapper) {
    return this.userCache.<T>get(_wrapper);
  }
}