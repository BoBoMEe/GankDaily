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

package com.bobomee.android.data.repo;

import com.bobomee.android.data.CacheRepository;
import com.bobomee.android.htttp.bean.GankCategory;
import com.bobomee.android.domain.executor.PostExecutionThread;
import com.bobomee.android.domain.executor.ThreadExecutor;
import com.bobomee.android.domain.interactor.UseCase;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for login and
 * retrieve a {@link GankCategory}.
 */
public class Category extends UseCase<GankCategory> {

  private final CacheRepository mReposRepository;
  private String category;
  private Integer count;
  private Integer page;
  private boolean update;

  public Category(CacheRepository reposRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.mReposRepository = reposRepository;
  }

  public void setParam(String category, Integer count, Integer page) {
    this.category = category;
    this.count = count;
    this.page = page;
  }

  @Override public Observable<GankCategory> buildUseCaseObservable(boolean update) {
    return this.mReposRepository.getCategoryData(category, count, page, update).map(Reply::getData);
  }
}
