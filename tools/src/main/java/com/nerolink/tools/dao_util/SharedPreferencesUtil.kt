package com.nerolink.tools.dao_util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtil private constructor() {

    companion object {
        fun putString(context: Context, key: String, value: String) {
            val editor = context.getSharedPreferences("user_data", Activity.MODE_PRIVATE).edit()
            editor.putString(key, value)
            editor.commit()
        }

        fun getString(context: Context, key: String):String {
            val pres = context.getSharedPreferences("user_data", Activity.MODE_PRIVATE)
            return pres.getString(key, "")
        }
    }


}