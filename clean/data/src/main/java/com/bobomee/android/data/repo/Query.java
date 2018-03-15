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

package com.bobomee.android.data.repo;

import com.bobomee.android.data.Repository;
import com.bobomee.android.domain.executor.PostExecutionThread;
import com.bobomee.android.domain.executor.ThreadExecutor;
import com.bobomee.android.domain.interactor.UseCase;
import com.bobomee.android.htttp.bean.GankQuery;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * @author BoBoMEe
 * @since 2017/6/21
 */
public class Query extends UseCase<GankQuery, Query.Params> {

  @Override protected Observable<GankQuery> buildUseCaseObservable(Params params, boolean update) {
    return this.mRepository.getQueryData(params.mQuery, params.mCategory, params.mCount,
        params.mPage, update).map(Reply::getData);
  }

  public static final class Params {

    private String mQuery;
    private String mCategory;
    private Integer mCount;
    private Integer mPage;

    public Params(String query, String category, Integer count, Integer page) {
      mQuery = query;
      mCategory = category;
      mCount = count;
      mPage = page;
    }

    public static Params forParams(String mQuery, String mCategory, Integer mCount, Integer mPage) {
      return new Params(mQuery, mCategory, mCount, mPage);
    }
  }

  private final Repository mRepository;

  protected Query(Repository reposRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.mRepository = reposRepository;
  }
}
