package com.example.leesanghoon.ridiremotecontroller.Util

import android.content.Context

object PrefUtils {

    fun getPrefLong(context: Context, key: String): Long {
        val pref = context.getSharedPreferences("p2pPref", Context.MODE_PRIVATE)
        return pref.getLong(key, 0)
    }

    fun setPrefLong(context: Context, key: String, value: Long) {
        val pref = context.getSharedPreferences("p2pPref", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putLong(key, value)
        editor.apply()
    }
}