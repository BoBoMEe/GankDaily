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

package com.bobomee.android.gank.io.di.category;

import com.bobomee.android.data.Repository;
import com.bobomee.android.data.di.scope.PerActivity;
import com.bobomee.android.data.repo.Category;
import com.bobomee.android.domain.executor.PostExecutionThread;
import com.bobomee.android.domain.executor.ThreadExecutor;
import com.bobomee.android.gank.io.mvp.category.CategoryContract;
import dagger.Module;
import dagger.Provides;

/**
 * Created on 2016/12/7.下午11:08.
 *
 * @author bobomee.
 *         https://github.com/BoBoMEe
 */
@Module class CategoryModule {

  @Provides @PerActivity Category provideCategory(Repository repository,
      ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    return new Category(repository, threadExecutor, postExecutionThread);
  }

  CategoryModule() {
  }

  static CategoryModule builder() {
    return new CategoryModule();
  }

  public CategoryModule setMeizhiView(CategoryContract.MeizhiView meizhiView) {
    this.mMeizhiView = meizhiView;
    return this;
  }

  CategoryContract.MeizhiView mMeizhiView;

  @Provides CategoryContract.MeizhiView provideReposListView() {
    return mMeizhiView;
  }
}
