package com.bobomee.android.gank.io.day.mvp;

import android.support.annotation.NonNull;
import com.bobomee.android.data.repo.Day;
import com.bobomee.android.domain.DomainConstants;
import com.bobomee.android.gank.io.category.mvp.MvpPresenter;
import com.bobomee.android.htttp.bean.GankDay;
import javax.inject.Inject;

/**
 * @author BoBoMEe
 * @since 2018/3/16
 */

public class DayPresenter extends MvpPresenter<DayContract.IDayView, Day, Day.Params, GankDay>
    implements DayContract.IDayPresenter {
  @Inject DayPresenter(@NonNull Day day, @NonNull DayContract.IDayView categoryView) {
    super(day, categoryView);
  }

  @Inject void setupListeners() {
    Day.Params params =
        Day.Params.forParams(DomainConstants.Y, DomainConstants.M, DomainConstants.D);
    buildParams(params);
  }

  @Override protected void doOnNext(GankDay gankDay, DayContract.IDayView mvpView) {
    super.doOnNext(gankDay, mvpView);
    mvpView.setDatas(gankDay);
  }
}
