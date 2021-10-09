package com.nm.mobiquityassignment.utils

import android.content.Context
import android.content.SharedPreferences
import com.nm.mobiquityassignment.utils.Constants.Companion.PREF_NAME


class SharedPreferencesUtils(val context: Context) {


    fun sharedPreferenceExist(key: String?): Boolean {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
        return !prefs.contains(key)
    }

    fun setInt(key: String?, value: Int) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String?): Int {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
        return prefs.getInt(key, 0)
    }

    fun setStr(key: String?, value: String?) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStr(key: String?): String? {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
        return prefs.getString(key, "DNF")
    }

    fun setBool(key: String?, value: Boolean) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
        val editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBool(key: String?): Boolean {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
        return prefs.getBoolean(key, false)
    }
}