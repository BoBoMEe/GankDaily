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

import com.bobomee.android.data.di.internal.components.ApplicationComponent;
import com.bobomee.android.data.di.scope.PerActivity;
import com.bobomee.android.gank.io.ui.MainActivity;
import dagger.Component;

/**
 * Created on 2016/12/7.下午11:08.
 *
 * @author bobomee.
 *         https://github.com/BoBoMEe
 */
@PerActivity @Component(dependencies = { ApplicationComponent.class }, modules = {
    /*ActivityModule.class,*/ CategoryModule.class
}) public interface CategoryComponent /*extends ActivityComponent*/ {

  void inject(MainActivity activity);

  enum Init {
    INSTANCE;

    private ApplicationComponent mApplicationComponent;
    private CategoryModule mCategoryModule;

    public void setCategoryModule(CategoryModule categoryModule) {
      mCategoryModule = categoryModule;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
      mApplicationComponent = applicationComponent;
    }

    public CategoryComponent initialize() {
      return DaggerCategoryComponent.builder().applicationComponent(mApplicationComponent)
          //.activityModule(new ActivityModule(activity))
          .categoryModule(mCategoryModule).build();
    }

  }
}
