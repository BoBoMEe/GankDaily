package com.bobomee.android.htttp.usecase;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.interactor.UseCase;
import rx.Observable;

/**
 * Created on 2016/11/26.下午8:36.
 *
 * @author bobomee.
 * @description
 */

public class GankCategory extends UseCase<com.bobomee.android.htttp.bean.GankCategory> {
  protected GankCategory(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
  }

  @Override protected Observable<com.bobomee.android.htttp.bean.GankCategory> buildUseCaseObservable() {
    return null;
  }
}
