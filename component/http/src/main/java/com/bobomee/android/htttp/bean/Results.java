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
 * Created on 2016/11/22.下午5:00.
 *
 * @author bobomee.
 * @description
 */

public class Results implements Parcelable {
  public String _id;
  public String createdAt;
  public String desc;
  public String ganhuo_id;
  public String publishedAt;
  public String readability;
  public String source;
  public String type;
  public String url;
  public boolean used;
  public String who;
  public String updateAt;
  public int height;
  public int width;
  public List<String> images;

  public String get_id() {
    return _id;
  }

  public void set_id(String __id) {
    _id = __id;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String _desc) {
    desc = _desc;
  }

  public String getGanhuo_id() {
    return ganhuo_id;
  }

  public void setGanhuo_id(String _ganhuo_id) {
    ganhuo_id = _ganhuo_id;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int _height) {
    height = _height;
  }

  public List<String> getImages() {
    return images;
  }

  public void setImages(List<String> _images) {
    images = _images;
  }

  public String getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(String _publishedAt) {
    publishedAt = _publishedAt;
  }

  public String getReadability() {
    return readability;
  }

  public void setReadability(String _readability) {
    readability = _readability;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String _source) {
    source = _source;
  }

  public String getType() {
    return type;
  }

  public void setType(String _type) {
    type = _type;
  }

  public String getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(String _updateAt) {
    updateAt = _updateAt;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String _url) {
    url = _url;
  }

  public boolean isUsed() {
    return used;
  }

  public void setUsed(boolean _used) {
    used = _used;
  }

  public String getWho() {
    return who;
  }

  public void setWho(String _who) {
    who = _who;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int _width) {
    width = _width;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String _createdAt) {
    createdAt = _createdAt;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this._id);
    dest.writeString(this.createdAt);
    dest.writeString(this.desc);
    dest.writeString(this.ganhuo_id);
    dest.writeString(this.publishedAt);
    dest.writeString(this.readability);
    dest.writeString(this.source);
    dest.writeString(this.type);
    dest.writeString(this.url);
    dest.writeByte(this.used ? (byte) 1 : (byte) 0);
    dest.writeString(this.who);
    dest.writeString(this.updateAt);
    dest.writeInt(this.height);
    dest.writeStringList(this.images);
  }

  public Results() {
  }

  protected Results(Parcel in) {
    this._id = in.readString();
    this.createdAt = in.readString();
    this.desc = in.readString();
    this.ganhuo_id = in.readString();
    this.publishedAt = in.readString();
    this.readability = in.readString();
    this.source = in.readString();
    this.type = in.readString();
    this.url = in.readString();
    this.used = in.readByte() != 0;
    this.who = in.readString();
    this.updateAt = in.readString();
    this.height = in.readInt();
    this.images = in.createStringArrayList();
  }

  public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
    @Override public Results createFromParcel(Parcel source) {
      return new Results(source);
    }

    @Override public Results[] newArray(int size) {
      return new Results[size];
    }
  };
}
