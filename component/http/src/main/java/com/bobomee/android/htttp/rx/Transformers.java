package com.bobomee.android.htttp.rx;

import com.bobomee.android.domain.exception.ApiException;
import com.bobomee.android.domain.bean.BaseData;
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
    return new Observable.Transformer<BaseData<T>, T>() {
      @Override
      public Observable<T> call(Observable<BaseData<T>> httpResponseObservable) {
        return httpResponseObservable.flatMap(new Func1<BaseData<T>, Observable<T>>() {
          @Override
          public Observable<T> call(BaseData<T> tGankHttpResponse) {
            if(!tGankHttpResponse.getError()) {
              return createData(tGankHttpResponse.getResults());
            } else {
              return Observable.error(new ApiException("服务器返回error"));
            }
          }
        });
      }
    };
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
