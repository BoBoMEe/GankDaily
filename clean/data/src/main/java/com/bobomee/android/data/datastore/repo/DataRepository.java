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
package com.bobomee.android.data.datastore.repo;

import com.bobomee.android.data.datastore.DataStoreFactory;
import com.bobomee.android.data.serializer.Wrapper;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * {@link Repository} for retrieving user data.
 */
@Singleton public class DataRepository implements Repository {

  private final DataStoreFactory userDataStoreFactory;

  /**
   * Constructs a {@link Repository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   */
  @Inject public DataRepository(DataStoreFactory dataStoreFactory) {
    this.userDataStoreFactory = dataStoreFactory;
  }

  @Override public <T> Observable<T> request(Wrapper<T> _wrapper) {
    final Repository userDataStore =
        this.userDataStoreFactory.create(_wrapper);

    return userDataStore.request(_wrapper);
  }
}
