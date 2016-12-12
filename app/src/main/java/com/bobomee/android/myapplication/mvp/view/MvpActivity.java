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

package com.bobomee.android.myapplication.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.bobomee.android.common.mvp.MvpPresenter;
import com.bobomee.android.common.mvp.MvpView;

/**
 * @author markzhai on 16/3/4
 * @version 1.0.0
 */
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends AppCompatActivity implements MvpView {

    public abstract P getPresenter();

    @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getPresenter().attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachView(isRetainingInstance());
        getPresenter().destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    public boolean isRetainingInstance() {
        return false;
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }
}
