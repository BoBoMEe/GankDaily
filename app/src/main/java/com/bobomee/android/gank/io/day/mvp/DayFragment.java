package com.bobomee.android.gank.io.day.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.bobomee.android.common.util.ToastUtil;
import com.bobomee.android.gank.io.R;
import com.bobomee.android.gank.io.base.MvpFragment;
import com.bobomee.android.gank.io.day.di.DayComponent;
import com.bobomee.android.htttp.bean.GankDay;
import javax.inject.Inject;

/**
 * @author BoBoMEe
 * @since 2018/3/16
 */

public class DayFragment extends MvpFragment<DayContract.IDayPresenter>
    implements DayContract.IDayView {

  @Inject DayPresenter mDayPresenter;
  @BindView(R.id.day_text) TextView dayText;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    DayComponent.Init.INSTANCE.initialize(mBaseActivity, this).inject(this);

    setPresenter(mDayPresenter);
  }

  public static DayFragment newInstance() {
    Bundle args = new Bundle();
    DayFragment fragment = new DayFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public View initFragmentView(LayoutInflater pInflater, ViewGroup pContainer,
      Bundle pSavedInstanceState) {
    return pInflater.inflate(R.layout.content_day, pContainer, false);
  }

  @Override public void setDatas(GankDay results) {
    String s = results.getResults().toString();
    ToastUtil.show(mBaseActivity, s);
    dayText.setText(s);
  }
}
