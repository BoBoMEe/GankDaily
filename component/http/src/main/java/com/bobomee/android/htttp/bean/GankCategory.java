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

package com.bobomee.android.htttp.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * Created on 2016/11/22.下午5:04.
 *
 * @author bobomee.
 *         https://github.com/BoBoMEe
 */

public class GankCategory extends BaseData<List<Results>> implements Parcelable {

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
  }

  public GankCategory() {
  }

  protected GankCategory(Parcel in) {
  }

  public static final Parcelable.Creator<GankCategory> CREATOR =
      new Parcelable.Creator<GankCategory>() {
        @Override public GankCategory createFromParcel(Parcel source) {
          return new GankCategory(source);
        }

        @Override public GankCategory[] newArray(int size) {
          return new GankCategory[size];
        }
      };
}
