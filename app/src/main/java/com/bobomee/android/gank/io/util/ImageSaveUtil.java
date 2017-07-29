/*
 *  Copyright (C) 2016.  BoBoMEe(wbwjx115@gmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.bobomee.android.gank.io.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import com.bumptech.glide.Glide;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Project ID：400YF17051<br/>
 * Resume:
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2017/7/29 汪波 first commit
 */
public class ImageSaveUtil {

  public static Observable<Uri> saveImage(Context context, String url, String title) {
    return Observable.create(new Observable.OnSubscribe<Bitmap>() {
      @Override public void call(Subscriber<? super Bitmap> subscriber) {
        Bitmap bitmap = null;
        try {
          bitmap = Glide.with(context).load(url).asBitmap() //必须
              .centerCrop().into(500, 500).get();
        } catch (Exception e) {
          subscriber.onError(e);
        }
        if (null == bitmap) {
          subscriber.onError(new Exception("无法下载图片"));
        }
        subscriber.onNext(bitmap);
        subscriber.onCompleted();
      }
    }).flatMap(bitmap -> {
      File saveDir = new File(Environment.getExternalStorageDirectory(), title);
      if (!saveDir.exists()) {
        saveDir.mkdir();
      }
      File file = new File(saveDir, title);
      try {
        FileOutputStream outputStream = new FileOutputStream(file);
        assert bitmap != null;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        outputStream.flush();
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      Uri uri = Uri.fromFile(file);
      // 通知图库更新
      Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
      context.sendBroadcast(scannerIntent);
      return Observable.just(uri);
    }).subscribeOn(Schedulers.io());
  }
}
