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

package com.bobomee.android.myapplication.di;

import com.bobomee.android.data.di.Dagger2Application;
import com.bobomee.android.data.di.scope.PerActivity;
import com.bobomee.android.data.di.internal.components.ActivityComponent;
import com.bobomee.android.data.di.internal.components.ApplicationComponent;
import com.bobomee.android.data.di.internal.modules.ActivityModule;
import com.bobomee.android.myapplication.ui.MvpActivity;
import com.bobomee.android.myapplication.ui.MainActivity;
import dagger.Component;
/**
 * Created on 2016/12/7.下午11:08.
 *
 * @author bobomee.
 *         https://github.com/BoBoMEe
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class},
        modules = {ActivityModule.class, ReposModule.class})
public interface ReposComponent extends ActivityComponent {

    void inject(MainActivity activity);

    class Init {
        private Init() {
        }

        public static <T extends MvpActivity> ReposComponent initialize(T activity) {
            return DaggerReposComponent.builder()
                .applicationComponent(Dagger2Application.get(activity).getComponent())
                .activityModule(new ActivityModule(activity))
                .reposModule(new ReposModule())
                .build();
        }
    }
}
