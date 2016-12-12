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

package com.bobomee.android.htttp.rx;

import com.bobomee.android.domain.exception.NotFoundException;
import com.bobomee.android.htttp.bean.BaseData;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created on 16/10/2.下午7:49.
 *
 * @author bobomee.
 * @description
 */

public class Transformers {
  public static <T> Observable.Transformer<T, T> switchSchedulers() {
    return observable -> observable.subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public static <T> Observable.Transformer<BaseData<T>, T> handleGankResult() {   //compose判断结果
    return httpResponseObservable -> httpResponseObservable.flatMap(
        new Func1<BaseData<T>, Observable<T>>() {
          @Override public Observable<T> call(BaseData<T> tGankHttpResponse) {
            if (!tGankHttpResponse.getError()) {
              return createData(tGankHttpResponse.getResults());
            } else {
              return Observable.error(new NotFoundException("服务器返回error"));
            }
      }
        });
  }

  public static <T> Observable<T> createData(final T t) {
    return Observable.create(new Observable.OnSubscribe<T>() {
      @Override
      public void call(Subscriber<? super T> subscriber) {
        try {
          subscriber.onNext(t);
          subscriber.onCompleted();
        } catch (Exception e) {
          subscriber.onError(e);
        }
      }
    });
  }
}
