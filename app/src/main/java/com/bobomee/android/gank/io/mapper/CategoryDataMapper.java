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

package com.bobomee.android.gank.io.mapper;

import com.bobomee.android.data.di.scope.PerActivity;
import com.bobomee.android.htttp.bean.GankCategory;
import com.bobomee.android.htttp.bean.Results;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Abner on 16/6/16.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
@PerActivity public class CategoryDataMapper {

  @Inject public CategoryDataMapper() {
  }

  public List<Results> transform(GankCategory gankCategory) {
    if (gankCategory == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }

    return gankCategory.getResults();
  }
}
