package com.bobomee.android.htttp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public enum HttpNetUtil {

    INSTANCE;

    private List<WeakReference<Networkreceiver>> networkreceivers;

    public interface Networkreceiver {

        void onConnected(boolean collect);
    }

    public void addNetWorkListener(Networkreceiver networkreceiver) {
        if (null == networkreceivers) {
            networkreceivers = new ArrayList<>();
        }
        networkreceivers.add(new WeakReference<>(networkreceiver));
    }

    public void removeNetWorkListener(Networkreceiver listener) {
        if (networkreceivers != null) {
            for (int i = 0; i < networkreceivers.size(); i++) {
                WeakReference<Networkreceiver> reference = networkreceivers.get(i);
                if (reference == null || reference.get() == null) {
                    networkreceivers.remove(i);
                    i--;
                    continue;
                }
                if (reference.get() == listener) {
                    networkreceivers.remove(i);
                    //i--;
                    break;
                }
            }
        }
    }

    public void clearNetWorkListeners() {
        if (networkreceivers != null) {
            networkreceivers.clear();
        }
    }

    private boolean isConnected = true;

    /**
     * 获取是否连接
     */
    public boolean isConnected() {
        return isConnected;
    }

    private void setConnected(boolean connected) {
        isConnected = connected;
    }

    /**
     * 判断网络连接是否存在
     */
    public void setConnected(Context context) {
        ConnectivityManager manager =
            (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean connected = false;
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            connected = info != null && info.isConnected();
        }
        setConnected(connected);

        if (networkreceivers != null) {
            for (int i = 0, z = networkreceivers.size(); i < z; i++) {
                WeakReference<Networkreceiver> listener = networkreceivers.get(i);
                if (listener != null) {
                    Networkreceiver networkreceiver = listener.get();
                    if (networkreceiver != null) networkreceiver.onConnected(connected);
                }
            }
        }
    }
}
