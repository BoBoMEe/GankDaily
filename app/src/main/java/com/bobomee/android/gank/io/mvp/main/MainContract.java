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

package com.bobomee.android.gank.io.mvp.main;

import com.bobomee.android.common.mvp.BaseContract.MvpPresenter;
import com.bobomee.android.common.mvp.BaseContract.MvpView;
import com.bobomee.android.htttp.bean.GankDay;
import java.util.List;

/**
 * @author BoBoMEe
 * @since 2017/6/23
 */
public interface MainContract {

  interface MainView extends MvpView<MainPresenter> {
  }

  interface MainPresenter extends MvpPresenter<MainView> {
  }
}
