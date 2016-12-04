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

import com.bobomee.android.data.di.core.PerActivity;
import com.bobomee.android.data.repo.Category;
import com.bobomee.android.data.repository.ReposRepository;
import com.bobomee.android.domain.executor.PostExecutionThread;
import com.bobomee.android.domain.executor.ThreadExecutor;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Abner on 16/5/27.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
@Module
public class ReposModule {

    @Provides @PerActivity Category provideCategory(ReposRepository reposRepository,
        ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new Category(reposRepository, threadExecutor, postExecutionThread);
    }

}
