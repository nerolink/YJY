package com.nerolink.resource_library.activity

import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nerolink.resource_library.R
import com.nerolink.resource_library.databinding.ActivityLoginBinding
import com.nerolink.resource_library.model.LoginModel


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_login)
        var binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val info = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
        val loginModel = LoginModel(
                logo = info.metaData.getInt("Logo"),
                account = info.metaData.getString("account"),
                password = info.metaData.getString("password")
        )
        binding.loginModel=loginModel
    }

}
