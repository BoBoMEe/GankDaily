package com.bobomee.android.data.di.internal.modules;

import com.bobomee.android.data.repository.ReposDataRepository;
import com.bobomee.android.data.repository.ReposRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Abner on 16/5/18.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton ReposRepository provideUserRepository(ReposDataRepository userDataRepository) {
        return userDataRepository;
    }


}
