package com.bobomee.android.gank.io.day.di;

import android.app.Activity;
import com.bobomee.android.data.di.Dagger2Application;
import com.bobomee.android.data.di.internal.components.ActivityComponent;
import com.bobomee.android.data.di.internal.components.ApplicationComponent;
import com.bobomee.android.data.di.internal.modules.ActivityModule;
import com.bobomee.android.data.di.scope.PerActivity;
import com.bobomee.android.gank.io.day.mvp.DayContract;
import com.bobomee.android.gank.io.day.mvp.DayFragment;
import dagger.Component;

/**
 * @author BoBoMEe
 * @since 2018/3/16
 */

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    DayModule.class, ActivityModule.class
}) public interface DayComponent extends ActivityComponent {

  void inject(DayFragment dayFragment);

  enum Init {
    INSTANCE;

    public DayComponent initialize(Activity activity, DayContract.IDayView dayView) {
      ApplicationComponent applicationComponent = Dagger2Application.get(activity).getComponent();
      return DaggerDayComponent.builder()
          .applicationComponent(applicationComponent)
          .activityModule(new ActivityModule(activity))
          .dayModule(new DayModule(dayView))
          .build();
    }

  }
}
