package com.bobomee.android.gank.io.category.meizhi.di;

import com.bobomee.android.data.di.scope.PerActivity;
import com.bobomee.android.gank.io.category.mvp.CategoryContract;
import dagger.Module;
import dagger.Provides;

/**
 * @author BoBoMEe
 * @since 2018/3/15
 */

@Module public class CategoryModule {

  private CategoryContract.ICategoryView mCategoryView;

  public CategoryModule(CategoryContract.ICategoryView categoryView) {
    mCategoryView = categoryView;
  }

  @Provides @PerActivity CategoryContract.ICategoryView providesCategoryView() {
    return mCategoryView;
  }
}
