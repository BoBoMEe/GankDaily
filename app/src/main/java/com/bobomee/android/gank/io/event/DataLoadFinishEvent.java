package com.bobomee.android.gank.io.event;

import com.bobomee.android.htttp.bean.Results;
import java.util.List;

/**
 * Project ID：400YF17051<br/>
 * Resume:
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2017/6/15 汪波 first commit
 */
public class DataLoadFinishEvent {

  final List<Results> datas;

  public DataLoadFinishEvent(List<Results> datas) {
    this.datas = datas;
  }

  public List<Results> getDatas() {
    return datas;
  }
}
