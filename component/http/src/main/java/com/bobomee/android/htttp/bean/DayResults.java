package com.bobomee.android.htttp.bean;

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
public class DayResults {

  public List<Results> 福利;
  public List<Results> Android;
  public List<Results> iOS;
  public List<Results> 休息视频;
  public List<Results> 前端;
  public List<Results> 拓展资源;
  public List<Results> App;
  public List<Results> 瞎推荐;

  public enum DAY_CATEGORY {
    ALL("all"), 福利("福利"), Android("Android"), //
    iOS("iOS"), 休息视频("休息视频"), 前端("前端"), //
    拓展资源("拓展资源"), App("App"), 瞎推荐("瞎推荐");//

    private final String value;

    DAY_CATEGORY(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }
}
