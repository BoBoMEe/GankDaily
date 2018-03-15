package com.bobomee.android.gank.io.ui.adapter;

import android.support.v4.app.Fragment;
import com.bobomee.android.gank.io.category.meizhi.mvp.MeizhiFragment;
import com.bobomee.android.gank.io.day.mvp.DayFragment;

/**
 * @author BoBoMEe
 * @since 2018/3/16
 */

public class MainFragmentFactory {

  public static Fragment getInstance(int pos) {
    Fragment result = new Fragment();
    switch (pos) {
      case 0:
        result = MeizhiFragment.newInstance();
        break;
      case 1:
        result = DayFragment.newInstance();
        break;
    }
    return result;
  }
}
