package com.bobomee.android.gank.io.ui.adapter;

import android.support.v4.app.Fragment;
import com.bobomee.android.gank.io.meizhi.mvp.MeizhiFragment;

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
    }
    return result;
  }
}
