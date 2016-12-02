package commonlib.mvp.impl2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created on 2016/11/22.下午10:38.
 *
 * @author bobomee.
 * @description
 */

public interface IViewDelegate {

  void init(LayoutInflater inflater, ViewGroup container);

  View getView();
}
