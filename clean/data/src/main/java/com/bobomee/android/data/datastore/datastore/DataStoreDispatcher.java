package com.bobomee.android.data.datastore.datastore;

import com.bobomee.android.data.datastore.DataStoreCloud;
import com.bobomee.android.data.serializer.UserCache;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.htttp.api.RestApi;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created on 2016/12/3.下午2:19.
 *
 * @author bobomee.
 * @description
 */

@Singleton
public class DataStoreDispatcher {

  private final RestApi restApi;
  private final UserCache userCache;
  private Wrapper wrapper;

  @Inject
  public DataStoreDispatcher(RestApi _restApi, UserCache _userCache) {
    restApi = _restApi;
    userCache = _userCache;
  }

  public DataStoreDispatcher setWrapper(Wrapper _wrapper) {
    wrapper = _wrapper;
    return this;
  }

  @SuppressWarnings("unchecked") public <T> DataStoreCloud<T> getDataStoreCloud() {
    String methodName = wrapper.getMethodName();

    DataStoreCloud dataStoreCloud;

    switch (methodName) {
      case "userEntityList":
        dataStoreCloud = new UserListCloud(restApi, userCache);
        break;
      case "getGirlList":
        dataStoreCloud = new GirlListCloud(restApi, userCache);
        break;
      default:
        dataStoreCloud = new DataStoreCloudDefault(restApi, userCache);
        break;
    }

    return dataStoreCloud;
  }
}
