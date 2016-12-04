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
package com.bobomee.android.data.di.internal.modules;

import android.app.Application;
import android.content.Context;
import com.bobomee.android.data.di.core.ApplicationContext;
import com.bobomee.android.data.executor.JobExecutor;
import com.bobomee.android.data.executor.UIThread;
import com.bobomee.android.data.serializer.UseCache;
import com.bobomee.android.data.serializer.UseCacheImpl;
import com.bobomee.android.domain.executor.PostExecutionThread;
import com.bobomee.android.domain.executor.ThreadExecutor;
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

  @Provides @Singleton UseCache provideUserCache(UseCacheImpl userCache) {
    return userCache;
  }

}
