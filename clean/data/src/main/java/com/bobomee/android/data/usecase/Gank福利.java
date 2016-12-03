package com.bobomee.android.data.usecase;

import com.bobomee.android.htttp.api.RestApi;
import com.bobomee.android.domain.bean.GankCategory;
import com.bobomee.android.domain.executor.PostExecutionThread;
import com.bobomee.android.domain.executor.ThreadExecutor;
import com.bobomee.android.domain.interactor.UseCase;
import rx.Observable;

/**
 * Created on 2016/11/26.下午8:38.
 *
 * @author bobomee.
 * @description
 */

public class Gank福利 extends UseCase<GankCategory> {

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
