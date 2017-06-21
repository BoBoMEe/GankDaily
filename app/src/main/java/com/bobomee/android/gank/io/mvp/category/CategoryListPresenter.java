/*
 *  Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.bobomee.android.gank.io.mvp.category;

import android.support.annotation.NonNull;
import com.bobomee.android.data.repo.Category;
import com.bobomee.android.domain.interactor.DefaultSubscriber;
import com.bobomee.android.gank.io.mapper.CategoryDataMapper;
import com.bobomee.android.htttp.bean.GankCategory;
import com.bobomee.android.gank.io.mvp.category.CategoryContract.CategoryView;
import com.bobomee.android.gank.io.mvp.category.CategoryContract.CategoryPresenter;
import com.bobomee.android.htttp.bean.Results;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Abner on 16/6/16.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
public class CategoryListPresenter implements CategoryPresenter {

  private final Category mCategoryUseCase;
  private final CategoryDataMapper mCategoryDataMapper;
  private final CategoryView<Results, CategoryPresenter> mCategoryView;

  private String mCategory;
  private Integer mCount;
  private Integer mPage;

  @Inject public CategoryListPresenter(@NonNull Category category,
      @NonNull CategoryView<Results, CategoryPresenter> categoryView,
      @NonNull CategoryDataMapper categoryDataMapper) {
    mCategoryUseCase = category;
    this.mCategoryView = categoryView;
    mCategoryDataMapper = categoryDataMapper;
  }

  @Inject void setupListeners() {
    mCategoryView.setPresenter(this);
  }

  /**
   * Initializes the presenter by start retrieving the user
   */
  @Override public void subscribe(boolean update) {
    mCategoryUseCase.setParam(mCategory, mCount, mPage);
    mCategoryUseCase.execute(new UserSubscriber(), update);
  }

  @Override public void unsubscribe() {
    mCategoryUseCase.unsubscribe();
  }

  @Override public void setParams(String category, int count, int page) {
    this.mCategory = category;
    this.mCount = count;
    this.mPage = page;
  }

  private class UserSubscriber extends DefaultSubscriber<GankCategory> {
    @Override public void onCompleted() {
    }

    @Override public void onError(Throwable e) {
    }

    @Override public void onNext(GankCategory reposEntity) {
      final List<Results> reposModels = mCategoryDataMapper.transform(reposEntity);
      mCategoryView.setDatas(reposModels);
    }
  }
}
