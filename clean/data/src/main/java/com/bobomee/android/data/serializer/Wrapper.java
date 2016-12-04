/*
 * Copyright (c) 2016. BoBoMEe(wbwjx115@gmail.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bobomee.android.data.serializer;

import java.lang.reflect.Type;

/**
 * Created on 16/9/25.下午8:56.
 *
 * @author bobomee.
 * @description
 */

public final class Wrapper<T> {

  private boolean isRefresh = true;
  private T t;//请求数据的类型,和返回的Observable<T>中的T一样
  private Type typeOfT;
  private String methodName;
  private Object[] params;

  private final Builder<T> mBuilder;// Wrapper的构建起builder

  public Wrapper(boolean isRefresh, Type _typeOfT, T _t, String _methodName, Object[] _params,
      Builder<T> _builder) {
    this.isRefresh = isRefresh;
    this.typeOfT = _typeOfT;
    this.t = _t;
    this.methodName = _methodName;
    this.params = _params;
    this.mBuilder = _builder;
  }

  public final boolean isRefresh() {
    return isRefresh;
  }

  public final Builder<T> getBuilder() {
    return mBuilder;
  }

  public final T getT() {
    return t;
  }

  public final String getMethodName() {
    return methodName;
  }

  public final Type getTypeOfT() {
    return typeOfT;
  }

  public final Object[] getParams() {
    return params;
  }

  public final String getKey() {

    String result = methodName;

    if (null != params && params.length > 0) {
      for (Object obj : params) {
        result += obj.toString();
      }
    }

    return result;
  }

  public static <T> Builder<T> builder(String _methodName, Object[] _params) {
    return new Builder<T>(_methodName, _params);
  }

  public static class Builder<T> {

    private boolean isRefresh = true;

    private T t;

    private Type mType;

    private String methodName;

    private Object[] params;

    private Wrapper<T> mTWrapper;

    public Builder(String _methodName, Object[] _params) {
      this.methodName = _methodName;
      this.params = _params;

      mTWrapper = WrapperManager.INSTANCE.get(getKey());
    }

    public Builder<T> isRefresh(boolean _isRefresh) {
      isRefresh = _isRefresh;
      if (null != mTWrapper) mTWrapper.isRefresh = _isRefresh;
      return this;
    }

    public Builder<T> T(T _t) {
      t = _t;
      if (null != mTWrapper) mTWrapper.t = _t;
      return this;
    }

    public Builder<T> typeOfT(Type _type) {
      this.mType = _type;
      if (null != mTWrapper) mTWrapper.typeOfT = _type;
      return this;
    }

    public Wrapper<T> build() {

      if (null == mTWrapper) {
        mTWrapper = new Wrapper<T>(isRefresh, mType, t, methodName, params, this);
        WrapperManager.INSTANCE.put(getKey(), mTWrapper);
      }

      return mTWrapper;
    }

    public final String getKey() {

      String result = methodName;

      if (null != params && params.length > 0) {
        for (Object obj : params) {
          result += obj.toString();
        }
      }

      return result;
    }
  }
}
