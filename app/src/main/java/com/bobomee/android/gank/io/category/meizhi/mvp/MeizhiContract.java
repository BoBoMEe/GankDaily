package com.bobomee.android.gank.io.category.meizhi.mvp;

import com.bobomee.android.common.mvp.BaseContract;
import com.bobomee.android.htttp.bean.Results;
import java.util.List;

/**
 * @author BoBoMEe
 * @since 2018/3/15
 */

public interface MeizhiContract {
  interface IMeizhiView extends BaseContract.MvpView<IMeizhiPresenter> {
    void setDatas(List<Results> datas);
  }

  interface IMeizhiPresenter extends BaseContract.MvpPresenter<IMeizhiView> {

  }
}
