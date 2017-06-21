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

package com.bobomee.android.htttp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public enum HttpNetUtil {

    INSTANCE;

    private List<WeakReference<NetWorkReceiver>> mNetWorkReceivers;

    public interface NetWorkReceiver {

        void onConnected(boolean collect);
    }

    public void addNetWorkListener(NetWorkReceiver networkreceiver) {
        if (null == mNetWorkReceivers) {
            mNetWorkReceivers = new ArrayList<>();
        }
        mNetWorkReceivers.add(new WeakReference<>(networkreceiver));
    }

    public void removeNetWorkListener(NetWorkReceiver listener) {
        if (mNetWorkReceivers != null) {
            for (int i = 0; i < mNetWorkReceivers.size(); i++) {
                WeakReference<NetWorkReceiver> reference = mNetWorkReceivers.get(i);
                if (reference == null || reference.get() == null) {
                    mNetWorkReceivers.remove(i);
                    i--;
                    continue;
                }
                if (reference.get() == listener) {
                    mNetWorkReceivers.remove(i);
                    break;
                }
            }
        }
    }

    public void clearNetWorkListeners() {
        if (mNetWorkReceivers != null) {
            mNetWorkReceivers.clear();
        }
    }

    private boolean mIsConnected = true;

    public boolean isConnected() {
        return mIsConnected;
    }

    private void setConnected(boolean connected) {
        mIsConnected = connected;
    }

    public void setConnected(Context context) {
        ConnectivityManager manager =
            (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean connected = false;
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            connected = info != null && info.isConnected();
        }
        setConnected(connected);

        if (mNetWorkReceivers != null) {
            for (int i = 0, z = mNetWorkReceivers.size(); i < z; i++) {
                WeakReference<NetWorkReceiver> listener = mNetWorkReceivers.get(i);
                if (listener != null) {
                    NetWorkReceiver networkreceiver = listener.get();
                    if (networkreceiver != null) networkreceiver.onConnected(connected);
                }
            }
        }
    }
}
