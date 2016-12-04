package com.bobomee.android.data.repository;

import com.bobomee.android.data.serializer.UserCache;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.domain.bean.GankCategory;
import com.bobomee.android.domain.bean.GankDay;
import com.bobomee.android.domain.bean.GankQuery;
import com.bobomee.android.domain.bean.ReposEntity;
import com.bobomee.android.domain.bean.UserEntity;
import com.bobomee.android.htttp.api.RestApi;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Abner on 16/5/27.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
@Singleton public class ReposCloudDataRepository implements ReposRepository {

  @Inject RestApi mReposApi;

  @Inject UserCache mUserCache;

  @Inject public ReposCloudDataRepository() {
  }

  @Override public Observable<GankQuery> getQueryData(String query, String category, Integer count,
      Integer page) {
    return mReposApi.getQueryData(query, category, count, page)
        .doOnNext(storeAction("getQueryData", new Object[] {
            query, category, count, page
        }));
  }

  @Override public Observable<GankDay> getGankData(Integer year, Integer month, Integer day) {
    return mReposApi.getGankData(year, month, day)
        .doOnNext(storeAction("getGankData", new Integer[] {
            year, month, day
        }));
  }

  @Override
  public Observable<GankCategory> getCategoryData(String category, Integer count, Integer page) {
    return mReposApi.getCategoryData(category, count, page)
        .doOnNext(storeAction("getCategoryData", new Object[] {
            category, count, page
        }));
  }

  @Override public Observable<GankCategory> getGirlList(Integer num, Integer page) {
    return mReposApi.getGirlList(num, page)
        .doOnNext(storeAction("getGirlList", new Integer[] { num, page }));
  }

  @Override public Observable<List<UserEntity>> userEntityList() {
    return mReposApi.userEntityList().doOnNext(storeAction("userEntityList", new String[]{}));
  }

  @Override public Observable<List<ReposEntity>> login(String user) {
    return mReposApi.login(user).doOnNext(storeAction("login", new String[] { user }));
  }

  private <T> Action1<T> storeAction(String method, Object[] params) {
    return _t -> mUserCache.put(Wrapper.<T>builder(method, params).T(_t).build());
  }
}
