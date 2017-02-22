/*
 * Copyright (C) 2017.  BoBoMEe(wbwjx115@gmail.com)
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

package com.bobomee.android.myapplication.mvp;

import android.content.Context;
import com.bobomee.android.common.mvp.BaseContract.MvpPresenter;
import com.bobomee.android.common.mvp.BaseContract.MvpView;
import com.bobomee.android.htttp.bean.Results;
import java.util.List;

/**
 * Project ID：400YF17050
 * Resume:     <br/>
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2017/2/22.汪波.
 */
public class CategoryContract {

  /**
   * Created by Abner on 16/6/16.
   * Email nimengbo@gmail.com
   * github https://github.com/nimengbo
   */
  public interface ReposListView extends MvpView {

    void userList(List<Results> userModels);

    void onItemClick(Results pResults);
  }

  /**
   * the repos presenter
   *
   * @param <V> the type of view
   */
  public interface ReposListPresenter<V extends MvpView> extends MvpPresenter<V> {

    void startDetail(Context pContext, Results pResults);
  }
}
