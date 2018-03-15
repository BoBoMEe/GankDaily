package com.bobomee.android.gank.io.meizhi.di;

import com.bobomee.android.data.di.scope.PerActivity;
import com.bobomee.android.gank.io.meizhi.mvp.MeizhiContract;
import dagger.Module;
import dagger.Provides;

/**
 * @author BoBoMEe
 * @since 2018/3/15
 */

@Module public class MeizhiModule {

  private MeizhiContract.MeizhiView mMeizhiView;

  public MeizhiModule(MeizhiContract.MeizhiView meizhiView) {
    mMeizhiView = meizhiView;
  }

  @Provides @PerActivity MeizhiContract.MeizhiView providesMeizhiView() {
    return mMeizhiView;
  }
}
