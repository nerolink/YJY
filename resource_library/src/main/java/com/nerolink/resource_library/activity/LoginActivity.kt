package com.nerolink.resource_library.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.nerolink.resource_library.R
import com.nerolink.resource_library.constant.APPConstant
import com.nerolink.resource_library.constant.WEBConstant
import com.nerolink.resource_library.databinding.ActivityLoginBinding
import com.nerolink.resource_library.model.LoginModel
import com.nerolink.resource_library.model.ResultLogin
import com.nerolink.resource_library.service.IEventManager
import com.nerolink.resource_library.service.MQTTService
import com.nerolink.resource_library.network.LoginApi
import com.nerolink.tools.network.LoggerInterceptor
import com.nerolink.tools.view.ToastUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null
    private var eventManager: IEventManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val info = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
        val loginModel = LoginModel(
                logo = info.metaData.getString("Logo"),
                account = info.metaData.getString("account"),
                password = "${info.metaData.getInt("password")}",
                type = info.metaData.getString("type"),
                onClick = ::login
        )
        binding?.loginModel = loginModel

        bindService(Intent(this, MQTTService::class.java), object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                eventManager = IEventManager.Stub.asInterface(service)
            }
        }, Context.BIND_AUTO_CREATE)
    }

    private fun login(id: String, password: String, type: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl(WEBConstant.HOST)
                .client(OkHttpClient.Builder().addInterceptor(LoggerInterceptor()).build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val request = retrofit.create(LoginApi::class.java)


        val params = when (type) {
            "teacher" -> hashMapOf("teacherId" to id, "password" to password)
            "student" -> hashMapOf("studentId" to id, "password" to password)
            else -> hashMapOf("teacherId" to id, "password" to password)
        }
        val path = when (type) {
            "teacher" -> WEBConstant.LOGIN_FOR_TEACHER
            "student" -> WEBConstant.LOGIN_FOR_STUDENT
            else -> WEBConstant.LOGIN_FOR_TEACHER
        }

//        val call: Call<ResultLogin> = request.postData<ResultLogin>(path = WEBConstant.LOGIN, params = params)
//        call.enqueue(object : Callback<ResultLogin> {
//            override fun onFailure(call: Call<ResultLogin>, t: Throwable) {
//                ToastUtil.showShortToast(baseContext, "login fail!!!")
//            }
//
//            override fun onResponse(call: Call<ResultLogin>, response: Response<ResultLogin>) {
//                ToastUtil.showLongToast(baseContext, response.body().toString())
//            }
//        })
//
        request.getLoginData(path = path, params = params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableObserver<ResultLogin>() {
                    override fun onComplete() {
                        println("finish!!")
                    }

                    override fun onNext(t: ResultLogin) {
                        ToastUtil.showLongToast(baseContext, t.toString())
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
    }

    private fun loginHTTPOK() {
        APPConstant.MYTOPIC = "TOPIC" + binding?.loginModel?.account
    }
}
