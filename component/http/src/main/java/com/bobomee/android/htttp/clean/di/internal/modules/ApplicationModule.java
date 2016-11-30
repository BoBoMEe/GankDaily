/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bobomee.android.htttp.clean.di.internal.modules;

import android.app.Application;
import android.content.Context;
import com.bobomee.android.htttp.clean.datastore.repo.DataRepository;
import com.bobomee.android.htttp.clean.datastore.repo.Repository;
import com.bobomee.android.htttp.clean.di.core.ApplicationContext;
import com.bobomee.android.htttp.clean.executor.JobExecutor;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.bobomee.android.htttp.clean.executor.UIThread;
import com.bobomee.android.htttp.clean.serializer.UserCache;
import com.bobomee.android.htttp.clean.serializer.UserCacheImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module public class ApplicationModule {
  private final Application application;

  public ApplicationModule(final Application application) {
    this.application = application;
  }

  @Singleton @Provides Application provideApplication() {
    return this.application;
  }

  @Provides @Singleton @ApplicationContext Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton UserCache provideUserCache(UserCacheImpl userCache) {
    return userCache;
  }

  @Provides @Singleton Repository provideUserRepository(DataRepository userDataRepository) {
    return userDataRepository;
  }
}
