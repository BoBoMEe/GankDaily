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
import com.bobomee.android.gank.io.mvp.category.CategoryContract.CategoryPresenter;
import com.bobomee.android.gank.io.mvp.category.CategoryContract.CategoryView;
import com.bobomee.android.htttp.bean.GankCategory;
import rx.Subscriber;

/**
 * Created by Abner on 16/6/16.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
public class CategoryListPresenter<V extends CategoryView>
    implements CategoryPresenter<V> {

  private final Category mCategoryUseCase;
  private final V mCategoryView;

  private String mCategory;
  private Integer mCount;
  private Integer mPage;

  public CategoryListPresenter(@NonNull Category category, @NonNull V categoryView) {
    mCategoryUseCase = category;
    this.mCategoryView = categoryView;
  }

  /**
   * Initializes the presenter by start retrieving the user
   */
  @Override public void subscribe(boolean update) {
    mCategoryUseCase.setParam(mCategory, mCount, mPage);
    mCategoryUseCase.execute(new Subscriber<GankCategory>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onNext(GankCategory gankCategory) {
        doOnNext(gankCategory,mCategoryView);
      }
    }, update);
  }

  @Override public void unsubscribe() {
    mCategoryUseCase.unsubscribe();
  }

  @Override public V getView() {
    return mCategoryView;
  }

  @Override public void setParams(String category, int count, int page) {
    this.mCategory = category;
    this.mCount = count;
    this.mPage = page;
  }

  protected void doOnNext(GankCategory category,V categoryView){

  }
}
