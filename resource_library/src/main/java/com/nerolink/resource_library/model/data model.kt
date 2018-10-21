package com.nerolink.resource_library.model

import android.databinding.Bindable
import android.databinding.Observable
import android.view.View

data class LoginModel( var logo: Int, var account: String,
                       var password: String,var clickListener: View.OnClickListener)

data class ResultLogin( var result: Boolean,  var info: String)


