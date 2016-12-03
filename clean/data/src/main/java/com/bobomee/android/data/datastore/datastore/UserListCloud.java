package com.bobomee.android.data.datastore.datastore;

import com.bobomee.android.data.datastore.DataStoreCloud;
import com.bobomee.android.data.serializer.UserCache;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.domain.bean.UserEntity;
import com.bobomee.android.htttp.api.RestApi;
import java.util.List;
import rx.Observable;

/**
 * Created on 2016/12/3.下午1:41.
 *
 * @author bobomee.
 * @description
 */

public class UserListCloud extends DataStoreCloud<List<UserEntity>> {
  public UserListCloud(RestApi _restApi, UserCache _userCache) {
    super(_restApi, _userCache);
  }

  @Override public Observable<List<UserEntity>> request(Wrapper<List<UserEntity>> _wrapper) {

    setWrapper(_wrapper);

    Observable<List<UserEntity>> mObservable = getRestApi().userEntityList();

    mObservable.doOnNext(getAction1());

    return mObservable;
  }
}
