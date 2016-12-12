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

public class GankQuery extends BaseData<List<Results>> implements Parcelable {

  public int count;

  public int getCount() {
    return count;
  }

  public void setCount(int _count) {
    count = _count;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.count);
  }

  public GankQuery() {
  }

  protected GankQuery(Parcel in) {
    this.count = in.readInt();
  }

  public static final Parcelable.Creator<GankQuery> CREATOR = new Parcelable.Creator<GankQuery>() {
    @Override public GankQuery createFromParcel(Parcel source) {
      return new GankQuery(source);
    }

    @Override public GankQuery[] newArray(int size) {
      return new GankQuery[size];
    }
  };
}
