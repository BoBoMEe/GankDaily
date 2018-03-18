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

package com.bobomee.android.gank.io.category.meizhi.mvp;

import android.support.annotation.NonNull;
import com.bobomee.android.data.repo.Category;
import com.bobomee.android.domain.DomainConstants;
import com.bobomee.android.gank.io.category.mapper.CategoryDataMapper;
import com.bobomee.android.gank.io.category.mvp.MvpPresenter;
import com.bobomee.android.htttp.bean.GankCategory;
import javax.inject.Inject;

/**
 * @author BoBoMEe
 * @since 2017/6/21
 */
public class MeizhiPresenter
    extends MvpPresenter<MeizhiContract.IMeizhiView, Category, Category.Params, GankCategory>
    implements MeizhiContract.IMeizhiPresenter {

  private final CategoryDataMapper mCategoryDataMapper;
  private int curPage = DomainConstants.FIRST_PAGE;

  @Inject MeizhiPresenter(@NonNull Category category,
      @NonNull MeizhiContract.IMeizhiView meizhiView,
      @NonNull CategoryDataMapper categoryDataMapper) {
    super(category, meizhiView);
    mCategoryDataMapper = categoryDataMapper;
  }

  @Inject void setupListeners() {
    updatparams();
  }

  private void updatparams() {
    Category.Params params =
        Category.Params.forParams(DomainConstants.福利, DomainConstants.PAGE_SIZE, curPage);
    buildParams(params);
  }

  public void addPage() {
    ++curPage;
    updatparams();
  }

  @Override
  protected void doOnNext(GankCategory category, MeizhiContract.IMeizhiView categoryView) {
    super.doOnNext(category, categoryView);
    categoryView.setDatas(mCategoryDataMapper.transform(category));
  }
}
