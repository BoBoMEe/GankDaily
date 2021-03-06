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
package com.bobomee.android.data.di.internal.components;

import android.app.Application;
import android.content.Context;
import com.bobomee.android.data.Repository;
import com.bobomee.android.data.di.Dagger2Application;
import com.bobomee.android.data.di.internal.modules.ApiModule;
import com.bobomee.android.data.di.internal.modules.ApplicationModule;
import com.bobomee.android.data.di.scope.ApplicationContext;
import com.bobomee.android.domain.executor.PostExecutionThread;
import com.bobomee.android.domain.executor.ThreadExecutor;
import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {
    ApplicationModule.class, ApiModule.class
}) public interface ApplicationComponent {
  void inject(Dagger2Application dagger2Application);

  @ApplicationContext Context applicationContext();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();

  Repository cacheRepository();

  enum Init {
    INSTANCE;

    public ApplicationComponent initialize(Application mApplication) {
      return DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(mApplication))
          .apiModule(new ApiModule())
          .build();
    }
  }
}
