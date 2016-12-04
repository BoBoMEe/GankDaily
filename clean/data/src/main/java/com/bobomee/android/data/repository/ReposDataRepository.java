package com.bobomee.android.data.repository;

import com.bobomee.android.data.serializer.Wrapper;
import com.bobomee.android.domain.bean.GankCategory;
import com.bobomee.android.domain.bean.GankDay;
import com.bobomee.android.domain.bean.GankQuery;
import com.bobomee.android.domain.bean.ReposEntity;
import com.bobomee.android.domain.bean.UserEntity;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * Created on 2016/12/3.下午8:17.
 *
 * @author bobomee.
 * @description
 */
@Singleton public class ReposDataRepository implements ReposRepository {

  private final ReposDataStoreFactory mDataStoreFactory;

  @Inject public ReposDataRepository(ReposDataStoreFactory _dataStoreFactory) {
    mDataStoreFactory = _dataStoreFactory;
  }

  @Override public Observable<GankQuery> getQueryData(String query, String category, Integer count,
      Integer page) {
    Type type = new TypeToken<GankQuery>() {
    }.getType();
    ReposRepository reposRepository =
        this.<GankQuery>create(type, "getQueryData", new Object[] { query, category, count, page });

    return reposRepository.getQueryData(query, category, count, page);
  }

  @Override public Observable<GankDay> getGankData(Integer year, Integer month, Integer day) {
    Type type = new TypeToken<GankDay>() {
    }.getType();
    ReposRepository reposRepository =
        this.<GankDay>create(type, "getGankData", new Integer[] { year, month, day });

    return reposRepository.getGankData(year, month, day);
  }

  @Override
  public Observable<GankCategory> getCategoryData(String category, Integer count, Integer page) {
    Type type = new TypeToken<GankCategory>() {
    }.getType();

    ReposRepository reposRepository =
        this.<GankCategory>create(type, "getCategoryData", new Object[] { category, count, page });

    return reposRepository.getCategoryData(category, count, page);
  }

  @Override public Observable<GankCategory> getGirlList(Integer num, Integer page) {
    Type type = new TypeToken<GankCategory>() {
    }.getType();

    ReposRepository reposRepository =
        this.<GankCategory>create(type, "getGirlList", new Integer[] { num, page });

    return reposRepository.getGirlList(num, page);
  }

  @Override public Observable<List<UserEntity>> userEntityList() {
    Type type = new TypeToken<List<UserEntity>>() {
    }.getType();

    ReposRepository reposRepository =
        this.<List<UserEntity>>create(type, "userEntityList", new String[] {});

    return reposRepository.userEntityList();
  }

  @Override public Observable<List<ReposEntity>> login(String user) {

    Type type = new TypeToken<List<ReposEntity>>() {
    }.getType();

    ReposRepository reposRepository =
        this.<List<ReposEntity>>create(type, "login", new String[] { user });

    return reposRepository.login(user);
  }

  private <T> ReposRepository create(Type _type, String method, Object[] params) {

    Wrapper<T> build = Wrapper.<T>builder(method, params).isRefresh(true).typeOfT(_type).build();

    return mDataStoreFactory.<T>create(build);
  }
}
