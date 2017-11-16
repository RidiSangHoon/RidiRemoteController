package com.example.leesanghoon.ridiremotecontroller

import android.content.Context

object PrefUtils {

    fun getPrefLong(context: Context) : Long {
        val pref = context.getSharedPreferences("p2pPref",Context.MODE_PRIVATE)
        return pref.getLong("p2pNodeId",0)
    }

    fun setPrefLong(context: Context, value: Long) {
        val pref = context.getSharedPreferences("p2pPref",Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putLong("p2pNodeId",value)
        editor.apply()
    }
}