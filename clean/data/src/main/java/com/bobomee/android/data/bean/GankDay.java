package com.bobomee.android.data.bean;

import com.bobomee.android.domain.bean.BaseData;
import java.util.List;

/**
 * Created on 2016/11/22.下午4:13.
 *
 * @author bobomee.
 * @description
 */

public class GankDay extends BaseData<GankDay.Results> {

  public List<String> category;

  public static class Results {
    public List<com.bobomee.android.data.bean.Results> Android;
    public List<com.bobomee.android.data.bean.Results> iOS;
    public List<com.bobomee.android.data.bean.Results> 休息视频;
    public List<com.bobomee.android.data.bean.Results> 拓展资源;
    public List<com.bobomee.android.data.bean.Results> 瞎推荐;
    public List<com.bobomee.android.data.bean.Results> 福利;
    public List<com.bobomee.android.data.bean.Results> App;
  }
}
