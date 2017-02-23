/*
 * Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.bobomee.android.gank.io.mvp.presenter;

import android.content.Context;
import com.bobomee.android.data.repo.Category;
import com.bobomee.android.domain.DomainConstants;
import com.bobomee.android.domain.interactor.DefaultSubscriber;
import com.bobomee.android.gank.io.mapper.ReposDataMapper;
import com.bobomee.android.gank.io.mvp.CategoryContract;
import com.bobomee.android.gank.io.mvp.CategoryContract.ReposListView;
import com.bobomee.android.gank.io.ui.DetailImageActivity;
import com.bobomee.android.htttp.bean.GankCategory;
import com.bobomee.android.htttp.bean.Results;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Abner on 16/6/16.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
public class CategoryListPresenter implements CategoryContract.ReposListPresenter {

    private final Category mGetRepos;
    private final ReposDataMapper mUserModelDataMapper;
    private final ReposListView<Results,CategoryListPresenter> mResultsCategoryListPresenterReposListView;
    private boolean isRequsted = false;

    @Inject public CategoryListPresenter(Category getRepos, ReposListView pReposListView,
                              ReposDataMapper userModelDataMapper) {
        mGetRepos = getRepos;
        this.mResultsCategoryListPresenterReposListView  = pReposListView;
        mUserModelDataMapper = userModelDataMapper;
    }

    @Inject
    void setupListeners() {
        mResultsCategoryListPresenterReposListView.setPresenter(this);
    }

    /**
     * Initializes the presenter by start retrieving the user
     */
    @Override public void subscribe(boolean update) {
        getUserList(update);
    }

    @Override public void unsubscribe() {
        mGetRepos.unsubscribe();
    }

    private void processUserList(GankCategory reposEntity) {
        final List<Results> reposModels = mUserModelDataMapper.transform(reposEntity);
        mResultsCategoryListPresenterReposListView.userList(reposModels);
    }

    private void getUserList(boolean update) {
        mGetRepos.setParam(DomainConstants.福利, DomainConstants.PAGE_SIZE,
            DomainConstants.FIRST_PAGE);
        mGetRepos.execute(new UserSubscriber(),update);
    }

    @Override public void startDetail(Context pContext, Results pResults) {
        DetailImageActivity.start(pContext, pResults);
    }

    @Override public boolean getRequsted() {
        return isRequsted;
    }

    @Override public void setRequested(boolean pRequested) {
        this.isRequsted = pRequested;
    }

    private class UserSubscriber extends DefaultSubscriber<GankCategory> {
        @Override
        public void onCompleted() {
        }
                                
        @Override
        public void onError(Throwable e) {
        }

        @Override public void onNext(GankCategory reposEntity) {
            processUserList(reposEntity);
        }
    }
}
