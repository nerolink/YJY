package com.nerolink.resource_library.activity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import com.nerolink.resource_library.R
import com.nerolink.resource_library.constant.APPConstant
import com.nerolink.tools.dao.SharedPreferencesUtil
import java.util.*
import kotlin.concurrent.timerTask

class WelcomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(baseContext).inflate(R.layout.activity_welcome, null)
        val ivLogo = view.findViewById<ImageView>(R.id.iv)
        val info = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
        ivLogo.setImageResource(info.metaData.getInt("Logo"))
        setContentView(view)

        val status = SharedPreferencesUtil.getString(context = applicationContext, key = "STATUS")
        val userId = SharedPreferencesUtil.getString(applicationContext, "USER_ID")


        val timer = Timer()
        timer.schedule(timerTask {
            if (status == "LOGIN") {
                APPConstant.USERID = userId
                APPConstant.LESSONID = userId + System.currentTimeMillis()
                startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
                finish()
            }
        }, 2000)
    }

}
