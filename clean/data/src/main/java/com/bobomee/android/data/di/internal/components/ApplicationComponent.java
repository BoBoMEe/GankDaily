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
import com.bobomee.android.data.datastore.repo.Repository;
import com.bobomee.android.data.di.Dagger2Application;
import com.bobomee.android.data.di.core.ApplicationContext;
import com.bobomee.android.data.di.internal.modules.ApiModule;
import com.bobomee.android.data.di.internal.modules.ApplicationModule;
import com.bobomee.android.data.di.internal.modules.RepositoryModule;
import com.bobomee.android.data.repository.ReposRepository;
import com.bobomee.android.domain.executor.PostExecutionThread;
import com.bobomee.android.domain.executor.ThreadExecutor;
import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {
    ApplicationModule.class, ApiModule.class, RepositoryModule.class
}) public interface ApplicationComponent {
  void inject(Dagger2Application _myApplication);

  @ApplicationContext Context applicationContext();

  //Exposed to sub-graphs.
  Application application();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();

  Repository userRepository();

  ReposRepository reposRepository();

  class Init {
    private Init() {
    }

    public static ApplicationComponent initialize(Dagger2Application _myApplication) {
      return DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(_myApplication))
          .apiModule(new ApiModule())
          .repositoryModule(new RepositoryModule())
          .build();
    }
  }
}