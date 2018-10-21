package com.nerolink.resource_library.model

import android.databinding.Bindable
import android.databinding.Observable
import android.view.View

 class LoginModel( var logo: String, var account: String,
                       var password: String,var clickListener: View.OnClickListener){
     public fun click() {
         clickListener.onClick(null)
     }
 }

data class ResultLogin( var result: Boolean,  var info: String)


