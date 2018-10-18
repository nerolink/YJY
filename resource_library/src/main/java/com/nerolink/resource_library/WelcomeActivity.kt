package com.nerolink.resource_library

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView

class WelcomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(baseContext).inflate(R.layout.activity_welcome, null)
        val ivLogo = view.findViewById<ImageView>(R.id.iv)
        val info=packageManager.getActivityInfo(componentName,PackageManager.GET_META_DATA)
        ivLogo.setImageResource(info.metaData.getInt("Logo"))
        setContentView(view)
    }
}
