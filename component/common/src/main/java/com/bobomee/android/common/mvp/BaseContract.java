/*
 * Copyright (C) 2017.  BoBoMEe(wbwjx115@gmail.com)
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

package com.bobomee.android.common.mvp;

/**
 * Base Contract for MVP
 */

public interface BaseContract {

  public interface MvpPresenter<V> {

    void subscribe(boolean update);

    void unsubscribe();
  }

  /**
   * Created on 2016/12/3.下午4:04.
   *
   * @author bobomee.
   */

  public interface MvpView<P> {

    void setPresenter(P presenter);

    P getPresenter();
  }
}
