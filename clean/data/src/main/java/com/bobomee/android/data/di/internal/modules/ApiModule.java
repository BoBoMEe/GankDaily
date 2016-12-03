package com.bobomee.android.data.di.internal.modules;

import com.bobomee.android.htttp.api.RestApi;
import com.bobomee.android.htttp.api.RestService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created on 2016/12/3.下午3:08.
 *
 * @author bobomee.
 * @description
 */

@Module
public class ApiModule {

  @Provides @Singleton RestApi provideRestApi(){
    return RestService.INSTANCE.getRestApi();
  }
}
