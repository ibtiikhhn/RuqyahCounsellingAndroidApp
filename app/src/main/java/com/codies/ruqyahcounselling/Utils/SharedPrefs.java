package com.codies.ruqyahcounselling.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static SharedPrefs sharedPrefs;
    protected Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;

    public SharedPrefs(Context context) {
        mContext = context;
        mSharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        mSharedPreferencesEditor = mSharedPreferences.edit();
    }

    public static synchronized SharedPrefs getInstance(Context context) {

        if (sharedPrefs == null) {
            sharedPrefs = new SharedPrefs(context.getApplicationContext());
        }
        return sharedPrefs;
    }

    public void loginAsUser(boolean value,String userId) {
        mSharedPreferencesEditor.putBoolean("loginAsUser", value);
        mSharedPreferencesEditor.putString("userId", userId);
        mSharedPreferencesEditor.commit();
    }

    public boolean isLoggedInAsUser() {
        return mSharedPreferences.getBoolean("loginAsUser", false);
    }

    public String getUserId() {
        return mSharedPreferences.getString("userId", null);
    }

    public void loginAsAdmin(boolean value,String adminId) {
        mSharedPreferencesEditor.putBoolean("loginAsAdmin", value);
        mSharedPreferencesEditor.putString("adminId", adminId);
        mSharedPreferencesEditor.commit();
    }

    public boolean isLoggedInAsAdmin() {
        return mSharedPreferences.getBoolean("loginAsAdmin", false);
    }

    public String getAdminId() {
        return mSharedPreferences.getString("adminId", null);
    }


    public void clearPrefrences() {
        mSharedPreferencesEditor.clear().commit();
    }
}
