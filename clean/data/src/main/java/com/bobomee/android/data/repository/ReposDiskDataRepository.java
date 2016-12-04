package com.bobomee.android.data.repository;

import com.bobomee.android.data.serializer.UserCache;
import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.domain.bean.GankCategory;
import com.bobomee.android.domain.bean.GankDay;
import com.bobomee.android.domain.bean.GankQuery;
import com.bobomee.android.domain.bean.ReposEntity;
import com.bobomee.android.domain.bean.UserEntity;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * Created on 2016/12/3.下午7:50.
 *
 * @author bobomee.
 * @description
 */
@Singleton public class ReposDiskDataRepository implements ReposRepository {

  private final UserCache mUserCache;

  @Inject public ReposDiskDataRepository(UserCache _userCache) {
    mUserCache = _userCache;
  }

  @Override public Observable<GankQuery> getQueryData(String query, String category, Integer count,
      Integer page) {

    Wrapper<GankQuery> getQueryData = this.<GankQuery>getWrapper("getQueryData", new Object[] {
        query, category, count, page
    });

    return mUserCache.<GankQuery>get(getQueryData);
  }

  @Override public Observable<GankDay> getGankData(Integer year, Integer month, Integer day) {

    Wrapper<GankDay> getGankData = this.<GankDay>getWrapper("getGankData", new Integer[] {
        year, month, day
    });

    return mUserCache.<GankDay>get(getGankData);
  }

  @Override
  public Observable<GankCategory> getCategoryData(String category, Integer count, Integer page) {

    Wrapper<GankCategory> getCategoryData =
        this.<GankCategory>getWrapper("getCategoryData", new Object[] {
            category, count, page
        });

    return mUserCache.<GankCategory>get(getCategoryData);
  }

  @Override public Observable<GankCategory> getGirlList(Integer num, Integer page) {

    Wrapper<GankCategory> getGirlList =
        this.<GankCategory>getWrapper("getGirlList", new Integer[] { num, page });

    return mUserCache.<GankCategory>get(getGirlList);
  }

  @Override public Observable<List<UserEntity>> userEntityList() {

    Wrapper<List<UserEntity>> userEntityList =
        this.<List<UserEntity>>getWrapper("userEntityList", new String[] {});

    return mUserCache.<List<UserEntity>>get(userEntityList);
  }

  @Override public Observable<List<ReposEntity>> login(String user) {

    Wrapper<List<ReposEntity>> login =
        this.<List<ReposEntity>>getWrapper("login", new String[] { user });

    return mUserCache.<List<ReposEntity>>get(login);
  }

  private <T> Wrapper<T> getWrapper(String method, Object[] params) {
    return Wrapper.<T>builder(method, params).build();
  }
}
