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

import com.bobomee.android.data.serializer.Wrapper;
import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link Wrapper} related data.
 */
public interface DataStore<T> {
  /**
   * Get an {@link Observable} which will emit a {@link T}.
   */
  Observable<T> request(final Wrapper<T> _wrapper);
}