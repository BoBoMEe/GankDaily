package com.bobomee.android.gank.io.category.meizhi.di;

import android.app.Activity;
import com.bobomee.android.data.di.Dagger2Application;
import com.bobomee.android.data.di.internal.components.ActivityComponent;
import com.bobomee.android.data.di.internal.components.ApplicationComponent;
import com.bobomee.android.data.di.internal.modules.ActivityModule;
import com.bobomee.android.data.di.scope.PerActivity;
import com.bobomee.android.gank.io.category.meizhi.mvp.MeizhiContract;
import com.bobomee.android.gank.io.category.meizhi.mvp.MeizhiFragment;
import dagger.Component;

/**
 * @author BoBoMEe
 * @since 2018/3/15
 */

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    MeizhiModule.class, ActivityModule.class
}) public interface MeizhiComponent extends ActivityComponent {

  void inject(MeizhiFragment meizhiFragment);

  enum Init {
    INSTANCE;

    public MeizhiComponent initialize(Activity activity, MeizhiContract.IMeizhiView meizhiView) {
      ApplicationComponent applicationComponent = Dagger2Application.get(activity).getComponent();
      return DaggerMeizhiComponent.builder()
          .applicationComponent(applicationComponent)
          .activityModule(new ActivityModule(activity))
          .meizhiModule(new MeizhiModule(meizhiView))
          .build();
    }

  }
}
