package com.nerolink.resource_library.activity

import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import com.nerolink.resource_library.R
import com.nerolink.resource_library.constant.APPConstant
import com.nerolink.resource_library.constant.WEBConstant
import com.nerolink.resource_library.databinding.ActivityLoginBinding
import com.nerolink.resource_library.model.LoginModel
import com.nerolink.resource_library.model.ResultLogin
import com.nerolink.tools.network.LoginApi
import com.nerolink.tools.view.ToastUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val info = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
        val loginModel = LoginModel(
                logo = info.metaData.getString("Logo"),
                account = info.metaData.getString("account"),
                password = info.metaData.getString("password"),
                clickListener = View.OnClickListener { v ->
                    login(info.metaData.getString("account"), info.metaData.getString("password"))
                }
        )
        binding?.loginModel = loginModel

    }

    private fun login(id: String, password: String) {
        val retrofit = Retrofit.Builder().baseUrl(WEBConstant.HOST)
                .addConverterFactory(GsonConverterFactory.create()).build()
        val request = retrofit.create(LoginApi::class.java)
        val params = hashMapOf(
                "teacherId" to id,
                "password" to password
        )
        val call: Call<ResultLogin> = request.postData<ResultLogin>(path = WEBConstant.LOGIN, params = params)
        call.enqueue(object : Callback<ResultLogin> {
            override fun onFailure(call: Call<ResultLogin>, t: Throwable) {
                ToastUtil.showShortToast(baseContext, "login fail!!!")
            }

            override fun onResponse(call: Call<ResultLogin>, response: Response<ResultLogin>) {
                ToastUtil.showLongToast(baseContext, response.body().toString())
            }
        })
    }

    private fun loginHTTPOK() {
        APPConstant.MYTOPIC = "TOPIC" + binding?.loginModel?.account

    }
}
