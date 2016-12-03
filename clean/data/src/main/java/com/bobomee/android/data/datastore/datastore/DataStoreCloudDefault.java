package com.bobomee.android.data.datastore.datastore;

import com.bobomee.android.data.datastore.DataStoreCloud;
import com.bobomee.android.data.serializer.UserCache;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.htttp.api.RestApi;
import rx.Observable;

/**
 * Created on 2016/12/3.下午1:41.
 *
 * @author bobomee.
 * @description
 */

public class DataStoreCloudDefault extends DataStoreCloud<Object> {
  public DataStoreCloudDefault(RestApi _restApi, UserCache _userCache) {
    super(_restApi, _userCache);
  }

  @Override public Observable<Object> request(Wrapper<Object> _wrapper) {
    return Observable.empty();
  }
}
