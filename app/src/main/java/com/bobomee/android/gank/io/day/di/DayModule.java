package com.bobomee.android.gank.io.day.di;

import com.bobomee.android.data.di.scope.PerActivity;
import com.bobomee.android.gank.io.day.mvp.DayContract;
import dagger.Module;
import dagger.Provides;

/**
 * @author BoBoMEe
 * @since 2018/3/16
 */

@Module public class DayModule {

  private DayContract.IDayView mDayView;

  public DayModule(DayContract.IDayView dayView) {
    mDayView = dayView;
  }

  @Provides @PerActivity DayContract.IDayView provideDayView() {
    return mDayView;
  }
}
