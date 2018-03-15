package com.bobomee.android.gank.io.meizhi.mvp;

import com.bobomee.android.common.mvp.BaseContract;
import com.bobomee.android.htttp.bean.Results;
import java.util.List;

/**
 * @author BoBoMEe
 * @since 2018/3/15
 */

public interface MeizhiContract {
  interface MeizhiView extends BaseContract.MvpView<MeizhiPresenter> {
    void setDatas(List<Results> datas);
  }

  interface MeizhiPresenter extends BaseContract.MvpPresenter<MeizhiView> {
  }
}
