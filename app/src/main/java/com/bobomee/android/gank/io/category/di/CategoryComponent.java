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

package com.bobomee.android.gank.io.category.di;

import android.content.Context;
import com.bobomee.android.data.di.Dagger2Application;
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
    CategoryModule.class
}) public interface CategoryComponent {

  void inject(MainActivity activity);

  enum Init {
    INSTANCE;

    private CategoryModule mCategoryModule;

    public void setCategoryModule(CategoryModule categoryModule) {
      mCategoryModule = categoryModule;
    }


    public CategoryComponent initialize(Context context) {
      ApplicationComponent applicationComponent = Dagger2Application.get(context).getComponent();
      return DaggerCategoryComponent.builder()
          .applicationComponent(applicationComponent)
          .categoryModule(mCategoryModule)
          .build();
    }

  }
}
