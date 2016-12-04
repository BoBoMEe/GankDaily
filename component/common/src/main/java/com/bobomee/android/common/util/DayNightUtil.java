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

package com.bobomee.android.common.util;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created on 2016/10/20.下午10:19.
 *
 * @author bobomee.
 * @description
 */

public class DayNightUtil {

    public static int getDayNightUiMode(Context _context) {
        return _context.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
    }

    public static boolean isNightUiMode(Context _context) {
        return getDayNightUiMode(_context) == Configuration.UI_MODE_NIGHT_YES;
    }

    public static boolean noNightUiMode(Context _context) {
        return getDayNightUiMode(_context) == Configuration.UI_MODE_NIGHT_NO;
    }

    public static int getNightMode(Context _context) {
        int mDayNightMode;
        if (DayNightUtil.isNightUiMode(_context)) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_YES;
        } else if (DayNightUtil.noNightUiMode(_context)) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_NO;
        } else {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
        }
        return mDayNightMode;
    }

    public static void switchDayNightMode(AppCompatActivity _activity) {
        boolean night = DayNightUtil.isNightUiMode(_activity);
        int mDayNightMode;
        if (night) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_NO;
        } else {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_YES;
        }
        _activity.getDelegate().setLocalNightMode(mDayNightMode);

        _activity.recreate();
    }
}
