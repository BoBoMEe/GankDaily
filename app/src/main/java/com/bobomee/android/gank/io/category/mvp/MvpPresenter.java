/*
 *  Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.bobomee.android.gank.io.category.mvp;

import android.support.annotation.NonNull;
import com.bobomee.android.common.mvp.BaseContract;
import com.bobomee.android.domain.interactor.DefaultSubscriber;
import com.bobomee.android.domain.interactor.UseCase;

/**
 * Created by Abner on 16/6/16.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
public class MvpPresenter<V extends BaseContract.MvpView, U extends UseCase, Params, Result>
    implements BaseContract.MvpPresenter<V> {

  private final U mUseCase;
  private final V mMvpView;

  public MvpPresenter(@NonNull U useCase, @NonNull V mvpView) {
    mUseCase = useCase;
    this.mMvpView = mvpView;
  }

  /**
   * Initializes the presenter by start retrieving the user
   */
  @Override public void subscribe(boolean update) {
    mUseCase.execute(new DefaultSubscriber<Result>() {

      @Override public void onNext(Result result) {
        doOnNext(result, mMvpView);
      }
    }, params(), update);
  }

  @Override public void unsubscribe() {
    mUseCase.unsubscribe();
  }

  @Override public V getView() {
    return mMvpView;
  }

  private Params mParams;

  public void buildParams(Params params) {
    mParams = params;
  }

  private Params params() {
    return mParams;
  }

  protected void doOnNext(Result result, V mvpView) {

  }
}
