package com.bobomee.android.data.serializer;

import java.util.HashMap;

/**
 * Created on 2016/12/3.下午10:14.
 *
 * @author bobomee.
 * @description
 */

public enum WrapperManager {

  INSTANCE;

  private HashMap<String, Wrapper> mWrapperHashMap = new HashMap<>();

  public void put(String key, Wrapper _wrapper) {
    mWrapperHashMap.put(key, _wrapper);
  }

  @SuppressWarnings("unchecked") public <T> Wrapper<T> get(String key) {
    return mWrapperHashMap.get(key);
  }

}
