/*
 *
 *  * Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package com.bobomee.android.gank.io.category.ui;

import android.support.annotation.NonNull;
import com.bobomee.android.data.repo.Category;
import com.bobomee.android.domain.DomainConstants;
import com.bobomee.android.gank.io.base.MvpPresenter;
import com.bobomee.android.htttp.bean.GankCategory;

/**
 * @author BoBoMEe
 * @since 2018/3/22
 */

public abstract class CategoryPresenter
    extends MvpPresenter<CategoryContract.ICategoryView, Category, Category.Params, GankCategory>
    implements CategoryContract.ICategoryPresenter {
  protected int curPage = DomainConstants.FIRST_PAGE;

  public CategoryPresenter(@NonNull Category useCase,
      @NonNull CategoryContract.ICategoryView mvpView) {
    super(useCase, mvpView);
  }

  public void addPage() {
    ++curPage;
    updatparams();
  }

  public void resetPage() {
    curPage = 0;
    updatparams();
  }

  public abstract void updatparams();
}
