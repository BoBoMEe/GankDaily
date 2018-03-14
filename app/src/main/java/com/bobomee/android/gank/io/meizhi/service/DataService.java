/*
 * Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.bobomee.android.gank.io.meizhi.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import com.bobomee.android.gank.io.meizhi.DataLoadFinishEvent;
import com.bobomee.android.gank.io.util.GlideUtil;
import com.bobomee.android.htttp.bean.Results;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/**
 * Created on 2016/12/11.下午2:25.
 *
 * @author bobomee.
 *         https://github.com/BoBoMEe
 */

public class DataService extends IntentService {

  private static String DATA = "data";

  public DataService() {
    super("");
  }

  public static void startService(Context context, List<Results> datas) {
    Intent intent = new Intent(context, DataService.class);

    intent.putParcelableArrayListExtra(DATA, (ArrayList<? extends Parcelable>) datas);
    context.startService(intent);
  }

  @Override protected void onHandleIntent(Intent intent) {
    if (null!= intent) {
      List<Results> datas = intent.getParcelableArrayListExtra(DATA);
      handleGirlItemData(datas);
    }
  }

  private void handleGirlItemData(List<Results> datas) {
    if (datas.size() != 0) {
      for (Results data : datas) {
        Bitmap bitmap = GlideUtil.load(this, data.url);
        if (bitmap != null) {
          data.setWidth(bitmap.getWidth());
          data.setHeight(bitmap.getHeight());
        }
      }
    }
    EventBus.getDefault().post(new DataLoadFinishEvent(datas));
  }
}

