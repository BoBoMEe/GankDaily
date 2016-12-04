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
package com.bobomee.android.common.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bobomee.android.common.permissions.Utils.checkCallingObjectSuitability;
import static com.bobomee.android.common.permissions.Utils.executePermissionsRequest;
import static com.bobomee.android.common.permissions.Utils.startAppSettingsScreen;

/**
 * Utility to request and check System permissions for apps targeting Android M (API >= 23).
 */
public class EasyPermission {
    private Object object;
    private String[] mPermissions;
    private String mRationale;
    private int mRequestCode;
    @StringRes private int mPositiveButtonText = android.R.string.ok;
    @StringRes private int mNegativeButtonText = android.R.string.cancel;

    private EasyPermission(Object object) {
        this.object = object;
    }

    public static EasyPermission with(Activity activity) {
        return new EasyPermission(activity);
    }

    public static EasyPermission with(Fragment fragment) {
        return new EasyPermission(fragment);
    }

    public EasyPermission permissions(String... permissions) {
        this.mPermissions = permissions;
        return this;
    }

    public EasyPermission rationale(String rationale) {
        this.mRationale = rationale;
        return this;
    }

    public EasyPermission addRequestCode(int requestCode) {
        this.mRequestCode = requestCode;
        return this;
    }

    public EasyPermission positveButtonText(@StringRes int positiveButtonText) {
        this.mPositiveButtonText = positiveButtonText;
        return this;
    }

    public EasyPermission nagativeButtonText(@StringRes int negativeButtonText) {
        this.mNegativeButtonText = negativeButtonText;
        return this;
    }

    public void request() {
        requestPermissions(object, mRationale, mPositiveButtonText, mNegativeButtonText,
                mRequestCode, mPermissions);
    }

    /**
     * Check if the calling context has a set of permissions.
     */
    public static boolean hasPermissions(Context context, String... perms) {
        // Always return true for SDK < M, let the system deal with the permissions
        if (!Utils.isOverMarshmallow()) {
            return true;
        }

        for (String perm : perms) {
            boolean hasPerm = (ContextCompat.checkSelfPermission(context, perm)
                    == PackageManager.PERMISSION_GRANTED);
            if (!hasPerm) {
                return false;
            }
        }

        return true;
    }

    /**
     * Request a set of permissions, showing rationale if the system requests it.
     */
    public static void requestPermissions(final Object object, String rationale,
            final int requestCode, final String... perms) {
        requestPermissions(object, rationale, android.R.string.ok, android.R.string.cancel,
                requestCode, perms);
    }

    /**
     * Request a set of permissions, showing rationale if the system requests it.
     */
    public static void requestPermissions(final Object object, String rationale,
            @StringRes int positiveButton, @StringRes int negativeButton, final int requestCode,
            final String... permissions) {

        checkCallingObjectSuitability(object);

        PermissionCallback mCallBack = (PermissionCallback) object;

        if (!Utils.isOverMarshmallow()) {
            mCallBack.onPermissionGranted(requestCode, Arrays.asList(permissions));
            return;
        }

        final List<String> deniedPermissions =
                Utils.findDeniedPermissions(Utils.getActivity(object), permissions);

        boolean shouldShowRationale = false;
        for (String perm : deniedPermissions) {
            shouldShowRationale =
                    shouldShowRationale || Utils.shouldShowRequestPermissionRationale(object, perm);
        }

        if (Utils.isEmpty(deniedPermissions)) {
            mCallBack.onPermissionGranted(requestCode, Arrays.asList(permissions));
        } else {

            final String[] deniedPermissionArray =
                    deniedPermissions.toArray(new String[deniedPermissions.size()]);

            if (shouldShowRationale) {
                Activity activity = Utils.getActivity(object);
                if (null == activity) {
                    return;
                }

                new AlertDialog.Builder(activity).setMessage(rationale)
                        .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                executePermissionsRequest(object, deniedPermissionArray,
                                        requestCode);
                            }
                        })
                        .setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                // act as if the permissions were denied
                                ((PermissionCallback) object).onPermissionDenied(requestCode,
                                        deniedPermissions);
                            }
                        })
                        .create()
                        .show();
            } else {
                executePermissionsRequest(object, deniedPermissionArray, requestCode);
            }
        }
    }



    /**
     * Handle the result of a permission request.
     */
    public static void onRequestPermissionsResult(Object object, int requestCode,
            String[] permissions, int[] grantResults) {
        checkCallingObjectSuitability(object);

        PermissionCallback mCallBack = (PermissionCallback) object;

        List<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }

        if (Utils.isEmpty(deniedPermissions)) {
            mCallBack.onPermissionGranted(requestCode, Arrays.asList(permissions));
        } else {
            mCallBack.onPermissionDenied(requestCode, deniedPermissions);
        }
    }

    /**
     * with a {@code null} argument for the negative buttonOnClickListener.
     */
    public static boolean checkDeniedPermissionsNeverAskAgain(final Object object, String rationale,
            @StringRes int positiveButton, @StringRes int negativeButton,
            List<String> deniedPerms) {
        return checkDeniedPermissionsNeverAskAgain(object, rationale, positiveButton,
                negativeButton, null, deniedPerms);
    }

    /**
     * 在OnActivityResult中接收判断是否已经授权
     * 使用{@link EasyPermission#hasPermissions(Context, String...)}进行判断
     *
     * If user denied permissions with the flag NEVER ASK AGAIN, open a dialog explaining the
     * permissions rationale again and directing the user to the app settings. After the user
     * returned to the app, {@link Activity#onActivityResult(int, int, Intent)} or
     * {@link Fragment#onActivityResult(int, int, Intent)} or
     * {@link android.app.Fragment#onActivityResult(int, int, Intent)} will be called with
     * {@link Utils#SETTINGS_REQ_CODE} as requestCode
     * <p>
     *
     * NOTE: use of this method is optional, should be called from
     * {@link PermissionCallback#onPermissionDenied(int, List)}
     *
     * @return {@code true} if user denied at least one permission with the flag NEVER ASK AGAIN.
     */
    public static boolean checkDeniedPermissionsNeverAskAgain(final Object object, String rationale,
            @StringRes int positiveButton, @StringRes int negativeButton,
            @Nullable DialogInterface.OnClickListener negativeButtonOnClickListener,
            List<String> deniedPerms) {
        boolean shouldShowRationale;
        for (String perm : deniedPerms) {
            shouldShowRationale = Utils.shouldShowRequestPermissionRationale(object, perm);

            if (!shouldShowRationale) {
                final Activity activity = Utils.getActivity(object);
                if (null == activity) {
                    return true;
                }

                new AlertDialog.Builder(activity).setMessage(rationale)
                        .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                Intent intent =
                                        new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                                intent.setData(uri);
                                startAppSettingsScreen(object, intent);
                            }
                        })
                        .setNegativeButton(negativeButton, negativeButtonOnClickListener)
                        .create()
                        .show();

                return true;
            }
        }

        return false;
    }
}
