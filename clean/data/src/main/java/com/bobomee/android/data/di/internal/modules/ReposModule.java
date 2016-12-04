package com.bobomee.android.data.di.internal.modules;

import com.bobomee.android.data.di.core.PerActivity;
import com.bobomee.android.data.repo.GetRepos;
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

    @Provides
    @PerActivity
    GetRepos provideLoginUseCase(ReposRepository reposRepository,
                                 ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread) {
        return new GetRepos(reposRepository, threadExecutor, postExecutionThread);
    }

}
