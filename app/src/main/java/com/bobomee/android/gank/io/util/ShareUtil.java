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
import android.net.Uri;
import com.bobomee.android.gank.io.R;

/**
 * Project ID：400YF17051<br/>
 * Resume:
 *
 * @author 汪波
 * @version 1.0
 * @see
 * @since 2017/7/29 汪波 first commit
 */
public class ShareUtil {

  public static void shareImage(Context context, Uri uri, String title) {
    Intent shareIntent = new Intent();
    shareIntent.setAction(Intent.ACTION_SEND);
    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
    shareIntent.setType("image/jpeg");
    context.startActivity(Intent.createChooser(shareIntent, title));
  }

  public static void share(Context context, String extraText) {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.action_share));
    intent.putExtra(Intent.EXTRA_TEXT, extraText);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(
        Intent.createChooser(intent, context.getString(R.string.action_share)));
  }
}
