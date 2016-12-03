package com.bobomee.android.data.datastore.datastore;

import com.bobomee.android.data.datastore.DataStoreCloud;
import com.bobomee.android.data.serializer.UserCache;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.htttp.api.RestApi;
import rx.Observable;

/**
 * Created on 2016/12/3.下午1:58.
 *
 * @author bobomee.
 * @description
 */

public class GirlListCloud<GankCategory>
    extends DataStoreCloud<com.bobomee.android.domain.bean.GankCategory> {
  public GirlListCloud(RestApi _restApi, UserCache _userCache) {
    super(_restApi, _userCache);
  }

  @Override public Observable<com.bobomee.android.domain.bean.GankCategory> request(
      Wrapper<com.bobomee.android.domain.bean.GankCategory> _wrapper) {

    setWrapper(_wrapper);

    Object[] params = _wrapper.getParams();

    Integer params1 = Integer.valueOf(params[0].toString());
    Integer params2 = Integer.valueOf(params[1].toString());

    Observable<com.bobomee.android.domain.bean.GankCategory> girlList =
        getRestApi().getGirlList(params1, params2);

    girlList.doOnNext(getAction1());

    return girlList;
  }
}
