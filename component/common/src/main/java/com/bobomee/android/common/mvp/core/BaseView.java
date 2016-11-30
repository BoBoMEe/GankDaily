package com.bobomee.android.common.mvp.core;

/**
 * Created on 16/9/23.下午11:13.
 *
 * @author bobomee.
 * @description
 */

public interface BaseView<P extends IPresenter> extends IView {
  void setPresenter(P _presenter);
}
