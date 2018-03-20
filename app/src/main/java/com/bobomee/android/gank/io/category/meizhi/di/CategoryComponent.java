package com.bobomee.android.gank.io.category.meizhi.di;

import android.app.Activity;
import com.bobomee.android.data.di.Dagger2Application;
import com.bobomee.android.data.di.internal.components.ActivityComponent;
import com.bobomee.android.data.di.internal.components.ApplicationComponent;
import com.bobomee.android.data.di.internal.modules.ActivityModule;
import com.bobomee.android.data.di.scope.PerActivity;
import com.bobomee.android.gank.io.category.mvp.CategoryContract;
import com.bobomee.android.gank.io.category.meizhi.mvp.MeizhiFragment;
import dagger.Component;

/**
 * @author BoBoMEe
 * @since 2018/3/15
 */

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    CategoryModule.class, ActivityModule.class
}) public interface CategoryComponent extends ActivityComponent {

  void inject(MeizhiFragment meizhiFragment);


  enum Init {
    INSTANCE;

    public CategoryComponent initialize(Activity activity, CategoryContract.ICategoryView categoryView) {
      ApplicationComponent applicationComponent = Dagger2Application.get(activity).getComponent();
      return DaggerCategoryComponent.builder()
          .applicationComponent(applicationComponent)
          .activityModule(new ActivityModule(activity))
          .categoryModule(new CategoryModule(categoryView))
          .build();
    }

  }
}
