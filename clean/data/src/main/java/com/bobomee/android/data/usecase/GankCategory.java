package com.bobomee.android.data.usecase;

import com.bobomee.android.domain.executor.PostExecutionThread;
import com.bobomee.android.domain.executor.ThreadExecutor;
import com.bobomee.android.domain.interactor.UseCase;
import rx.Observable;

/**
 * Created on 2016/11/26.下午8:36.
 *
 * @author bobomee.
 * @description
 */

public class GankCategory extends UseCase<com.bobomee.android.domain.bean.GankCategory> {
  protected GankCategory(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
  }

  @Override protected Observable<com.bobomee.android.domain.bean.GankCategory> buildUseCaseObservable() {
    return null;
  }
}
