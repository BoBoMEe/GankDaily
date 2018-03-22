package com.bobomee.android.gank.io.ui.adapter;

import com.bobomee.android.gank.io.category.meizhi.mvp.MeizhiFragment;
import com.bobomee.android.gank.io.category.ui.CategoryFragment;

/**
 * @author BoBoMEe
 * @since 2018/3/16
 */

public class CategoryFragmentFactory {

  public static CategoryFragment getInstance(int pos) {
    CategoryFragment result = new CategoryFragment();
    switch (pos) {
      case 0:
        result = MeizhiFragment.newInstance();
        break;
      case 1:
        //result = DayFragment.newInstance();
        break;
    }
    return result;
  }
}
