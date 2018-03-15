package com.bobomee.android.gank.io.category.meizhi.di;

import com.bobomee.android.data.di.scope.PerActivity;
import com.bobomee.android.gank.io.category.meizhi.mvp.MeizhiContract;
import dagger.Module;
import dagger.Provides;

/**
 * @author BoBoMEe
 * @since 2018/3/15
 */

@Module public class MeizhiModule {

  private MeizhiContract.IMeizhiView mMeizhiView;

  public MeizhiModule(MeizhiContract.IMeizhiView meizhiView) {
    mMeizhiView = meizhiView;
  }

  @Provides @PerActivity MeizhiContract.IMeizhiView providesMeizhiView() {
    return mMeizhiView;
  }
}
