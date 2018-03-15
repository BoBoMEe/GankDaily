package com.bobomee.android.gank.io.day.mvp;

import com.bobomee.android.common.mvp.BaseContract;
import com.bobomee.android.htttp.bean.GankDay;

/**
 * @author BoBoMEe
 * @since 2018/3/16
 */

public interface DayContract {

  interface IDayView extends BaseContract.MvpView<IDayPresenter> {
    void setDatas(GankDay results);
  }

  interface IDayPresenter extends BaseContract.MvpPresenter<IDayView> {

  }
}
