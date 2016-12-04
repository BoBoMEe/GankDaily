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

import android.app.Activity;
import android.content.Context;
import com.bobomee.android.data.di.core.ActivityContext;
import com.bobomee.android.data.di.core.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created on 16/9/27.上午9:33.
 *
 * @author bobomee.
 * @description
 */
@Module public class ActivityModule {
  private final Activity activity;

  public ActivityModule(final Activity activity) {
    this.activity = activity;
  }

  /**
   * Expose the activity to dependents in the graph.
   */
  @Provides @PerActivity Activity provideActivity() {
    return activity;
  }

  @Provides @ActivityContext @PerActivity Context provideActivityContext() {
    return activity;
  }
}
