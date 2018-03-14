package com.bobomee.android.gank.io.meizhi.mvp;

import com.bobomee.android.gank.io.mvp.category.CategoryContract;
import com.bobomee.android.htttp.bean.Results;
import java.util.List;

/**
 * @author BoBoMEe
 * @since 2018/3/15
 */

public interface MeizhiContract {
  interface MeizhiView extends CategoryContract.CategoryView<MeizhiPresenter> {
    void setDatas(List<Results> datas);
  }

  interface MeizhiPresenter extends CategoryContract.CategoryPresenter<MeizhiView> {
  }
}
