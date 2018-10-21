package com.nerolink.tools.view

import android.content.Context
import android.widget.Toast

class ToastUtil {
    companion object {
        fun showShortToast(context: Context, content: String) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
        }

        fun showLongToast(context: Context, content: String) {
            Toast.makeText(context, content, Toast.LENGTH_LONG).show()
        }
    }
}