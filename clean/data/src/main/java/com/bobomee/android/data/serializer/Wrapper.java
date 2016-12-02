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

/**
 * Created on 16/9/25.下午8:56.
 *
 * @author bobomee.
 * @description
 */

public final class Wrapper<T> {

  private boolean refresh = true;//是否请求最新数据
  private final T t;//请求数据的类型,和返回的Observable<T>中的T一样
  private final Object[] params;//请求的参数
  private final String methodName;// Retrofit接口中定义的方法名,供请求调用
  private final Builder<T> mBuilder;// Wrapper的构建起builder

  public Wrapper(boolean _refresh, T _t, Object[] _params,
      String _methodName, Builder<T> _builder) {
    this.refresh = _refresh;
    this.t = _t;
    this.params = _params;
    this.methodName = _methodName;
    this.mBuilder = _builder;
  }

  public final Builder<T> getBuilder() {
    return mBuilder;
  }

  public final boolean isRefresh() {
    return refresh;
  }

  public final T getT() {
    return t;
  }

  public final String getMethodName() {
    return methodName;
  }

  public final Object[] getParams() {
    return params;
  }

  public final String getUnique() {
    Object[] params = getParams();

    String result = getMethodName();

    if (null != params && params.length > 0) {
      for (Object o : params) {
        result += o.toString();
      }
    }
    return result;
  }

  public static<T> Builder<T> builder() {
    return new Builder<T>();
  }

  public static class Builder<T> {

    private boolean refresh = true;

    private T t;

    private Object[] params;

    private String methodName;

    public Builder<T> T(T _t) {
      t = _t;
      return this;
    }

    public Builder<T> method(String method) {
      methodName = method;
      return this;
    }

    public Builder<T> params(Object[] params) {
      this.params = params;
      return this;
    }

    public Builder<T> isRefresh(boolean isRefresh) {
      this.refresh = isRefresh;
      return this;
    }

    public Wrapper<T> build() {
      return new Wrapper<T>(refresh, t, params, methodName, this);
    }
  }
}
