package com.bobomee.android.htttp.usecase;

import com.bobomee.android.htttp.api.RestApi;
import com.bobomee.android.htttp.bean.GankCategory;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.interactor.UseCase;
import rx.Observable;

/**
 * Created on 2016/11/26.下午8:38.
 *
 * @author bobomee.
 * @description
 */

public class Gank福利 extends UseCase<com.bobomee.android.htttp.bean.GankCategory> {

  private final RestApi mRestApi;
  private int mNum;
  private int mPage;

  protected Gank福利(RestApi _restApi, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.mRestApi = _restApi;
  }

  public void setParams(int _num, int _page) {
    this.mNum = _num;
    this.mPage = _page;
  }

  @Override protected Observable<GankCategory> buildUseCaseObservable() {
    return mRestApi.getGirlList(mNum, mPage);
  }
}
