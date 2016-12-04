package com.bobomee.android.myapplication.di;

import com.bobomee.android.data.di.Dagger2Application;
import com.bobomee.android.data.di.core.PerActivity;
import com.bobomee.android.data.di.internal.components.ActivityComponent;
import com.bobomee.android.data.di.internal.components.ApplicationComponent;
import com.bobomee.android.data.di.internal.modules.ActivityModule;
import com.bobomee.android.data.di.internal.modules.ReposModule;
import com.bobomee.android.myapplication.base.MvpActivity;
import com.bobomee.android.myapplication.ui.MainActivity;
import dagger.Component;

/**
 * Created by Abner on 16/5/27.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
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
