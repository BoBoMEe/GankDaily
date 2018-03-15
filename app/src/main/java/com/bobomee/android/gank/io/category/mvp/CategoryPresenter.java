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

package com.bobomee.android.gank.io.category.mvp;

import android.support.annotation.NonNull;
import com.bobomee.android.common.mvp.BaseContract;
import com.bobomee.android.data.repo.Category;
import com.bobomee.android.domain.interactor.DefaultSubscriber;
import com.bobomee.android.htttp.bean.GankCategory;

/**
 * Created by Abner on 16/6/16.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
public class CategoryPresenter<V extends BaseContract.MvpView>
    implements BaseContract.MvpPresenter<V> {

  private final Category mCategoryUseCase;
  private final V mCategoryView;

  public CategoryPresenter(@NonNull Category category, @NonNull V categoryView) {
    mCategoryUseCase = category;
    this.mCategoryView = categoryView;
  }

  /**
   * Initializes the presenter by start retrieving the user
   */
  @Override public void subscribe(boolean update) {
    mCategoryUseCase.execute(new DefaultSubscriber<GankCategory>() {

      @Override public void onNext(GankCategory gankCategory) {
        doOnNext(gankCategory, mCategoryView);
      }
    }, params(), update);
  }

  @Override public void unsubscribe() {
    mCategoryUseCase.unsubscribe();
  }

  @Override public V getView() {
    return mCategoryView;
  }

  private Category.Params mParams;

  public void buildParams(String category, int count, int page) {
    mParams = Category.Params.forParams(category, count, page);
  }

  private Category.Params params() {
    return mParams;
  }

  protected void doOnNext(GankCategory category, V categoryView) {

  }
}
