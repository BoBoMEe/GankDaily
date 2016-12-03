package com.bobomee.android.domain.bean;

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
    public List<com.bobomee.android.domain.bean.Results> Android;
    public List<com.bobomee.android.domain.bean.Results> iOS;
    public List<com.bobomee.android.domain.bean.Results> 休息视频;
    public List<com.bobomee.android.domain.bean.Results> 拓展资源;
    public List<com.bobomee.android.domain.bean.Results> 瞎推荐;
    public List<com.bobomee.android.domain.bean.Results> 福利;
    public List<com.bobomee.android.domain.bean.Results> App;
  }
}
