package com.nerolink.resource_library.model

import android.databinding.Bindable
import android.databinding.Observable
import android.view.View

class LoginModel(var logo: String, var account: String,
                 var password: String, var type: String, var onClick: (ac: String, pw: String, ty: String) -> Unit) {
    fun click() = onClick(account, password, type)
}

data class ResultLogin(var result: Boolean, var info: String)


