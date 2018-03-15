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
import com.bobomee.android.htttp.bean.GankDay;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * @author BoBoMEe
 * @since 2017/6/21
 */
public class Day extends UseCase<GankDay, Day.Params> {

  private final Repository mRepository;

  @Override protected Observable<GankDay> buildUseCaseObservable(Params params, boolean update) {
    return this.mRepository.getGankDayData(params.mYear, params.mMonth, params.mDay, update)
        .map(Reply::getData);
  }

  public static final class Params {

    private Integer mYear;
    private Integer mMonth;
    private Integer mDay;

    public Params(Integer year, Integer month, Integer day) {
      this.mYear = year;
      this.mMonth = month;
      this.mDay = day;
    }

    public static Day.Params forParams(Integer year, Integer month, Integer day) {
      return new Day.Params(year, month, day);
    }
  }

  protected Day(Repository reposRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.mRepository = reposRepository;
  }
}
