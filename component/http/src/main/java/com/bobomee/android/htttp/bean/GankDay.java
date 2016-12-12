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
 * Created on 2016/11/22.下午4:13.
 *
 * @author bobomee.
 * @description
 */

public class GankDay extends BaseData<GankDay.Results> implements Parcelable {

  public List<String> category;

  //all | 福利 | Android | iOS | 休息视频 | 前端 | 拓展资源 | App | 瞎推荐 |


  public static class Results {
    public List<com.bobomee.android.htttp.bean.Results> 福利;
    public List<com.bobomee.android.htttp.bean.Results> Android;
    public List<com.bobomee.android.htttp.bean.Results> iOS;
    public List<com.bobomee.android.htttp.bean.Results> 休息视频;
    public List<com.bobomee.android.htttp.bean.Results> 前端;
    public List<com.bobomee.android.htttp.bean.Results> 拓展资源;
    public List<com.bobomee.android.htttp.bean.Results> App;
    public List<com.bobomee.android.htttp.bean.Results> 瞎推荐;
  }

  public List<String> getCategory() {
    return category;
  }

  public void setCategory(List<String> _category) {
    category = _category;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeStringList(this.category);
  }

  public GankDay() {
  }

  protected GankDay(Parcel in) {
    this.category = in.createStringArrayList();
  }

  public static final Parcelable.Creator<GankDay> CREATOR = new Parcelable.Creator<GankDay>() {
    @Override public GankDay createFromParcel(Parcel source) {
      return new GankDay(source);
    }

    @Override public GankDay[] newArray(int size) {
      return new GankDay[size];
    }
  };
}
