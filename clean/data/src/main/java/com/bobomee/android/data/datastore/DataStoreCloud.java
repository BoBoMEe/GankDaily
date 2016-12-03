package com.bobomee.android.data.datastore;

import com.bobomee.android.data.datastore.repo.Repository;
import com.bobomee.android.data.serializer.UserCache;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.htttp.api.RestApi;
import rx.functions.Action1;

/**
 * Created on 2016/12/3.下午1:39.
 *
 * @author bobomee.
 * {@link Repository} implementation based on connections to the api (Cloud).
 */

public abstract class DataStoreCloud<T> implements DataStore<T> {

  private final RestApi restApi;
  private final UserCache userCache;
  private Wrapper<T> wrapper;
  private Action1<T> mAction1 = new Action1<T>() {
    @Override public void call(T _t) {
      if (null != wrapper) {
        Wrapper<T> build = wrapper.getBuilder().T(_t).build();
        if (null != userCache) {
          userCache.put(build);
        }
      }
    }
  };
  /**
   * Construct a {@link Repository} based on connections to the api (Cloud).
   *
   * @param _restApi The {@link RestApi} implementation to use.
   * @param _userCache A {@link UserCache} to cache data retrieved from the api.
   */
  public DataStoreCloud(RestApi _restApi, UserCache _userCache) {
    restApi = _restApi;
    userCache = _userCache;
  }

  public RestApi getRestApi() {
    return restApi;
  }

  public UserCache getUserCache() {
    return userCache;
  }

  public Wrapper<T> getWrapper() {
    return wrapper;
  }

  public void setWrapper(Wrapper<T> _wrapper) {
    wrapper = _wrapper;
  }

  public Action1<T> getAction1() {
    return mAction1;
  }

  public void setAction1(Action1<T> _action1) {
    mAction1 = _action1;
  }
}


