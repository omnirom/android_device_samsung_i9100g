/*
 * Copyright (C) 2012 The CyanogenMod Project
 * Copyright (C) 2014 The OmniROM Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.omnirom.device;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceManager;

public class Hspa extends ListPreference implements OnPreferenceChangeListener {

    private static final String SERVICE_MODE_PACKAGE = "org.omnirom.samsungservicemode";
    private Context mCtx;

    public Hspa(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnPreferenceChangeListener(this);
        mCtx = context;
    }

    public static boolean isSupported(Context context) {
        boolean hasServiceMode;
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(SERVICE_MODE_PACKAGE, PackageManager.GET_ACTIVITIES);
            hasServiceMode = true;
        } catch (PackageManager.NameNotFoundException e) {
            hasServiceMode = false;
        }

        return hasServiceMode;
    }

    /**
     * Restore hspa setting from SharedPreferences. (Write to kernel.)
     * @param context       The context to read the SharedPreferences from
     */
    public static void restore(Context context) {
        if (!isSupported(context)) {
            return;
        }

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        sendIntent(context, sharedPrefs.getString(DeviceSettings.KEY_HSPA, "23"));
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        sendIntent(mCtx, (String) newValue);
        return true;
    }

    private static void sendIntent(Context context, String value) {
        Intent i = new Intent("org.omnirom.SamsungServiceMode.EXECUTE");
        i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        i.putExtra("sub_type", 20); // HSPA Setting
        i.putExtra("data", value);
        context.sendBroadcast(i);
    }
}
