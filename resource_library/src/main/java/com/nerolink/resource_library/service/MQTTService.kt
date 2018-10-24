package com.nerolink.resource_library.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.IBinder
import com.nerolink.resource_library.model.EventBusMessage
import com.nerolink.resource_library.network.MQTTClientInstance
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.greenrobot.eventbus.EventBus
import java.util.*

class MQTTService : Service() {

    private var isRunning: Boolean = false
    private val client = MQTTClientInstance.instance
    private val eventManager= object : IEventManager.Stub() {
        override fun registEvent(busType: String?, mqttMessageType: Int, eventBusMessageType: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    companion object {
        val events = mutableListOf<Triple<String, Int, Int>>()

    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder {
        return eventManager
    }
    private fun init(): Unit {
        isRunning = true
        client.setCallback(object : MqttCallback {
            override fun messageArrived(topic: String?, message: MqttMessage?) {
            }

            override fun connectionLost(cause: Throwable?) {
                EventBus.getDefault().post(EventBusMessage(EventBusMessage.EventBusMessageType.NETWORKOFFLINE))
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        println("reconnecting....."
                        )
                    }
                }, 10, 2000)

            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun connect() {
        val cn = ComponentName(this, MQTTService::class.java)
        val info = packageManager.getServiceInfo(cn, PackageManager.GET_META_DATA)
        client.connect()
        info.metaData.keySet().forEach { key ->
            client.subscribe(info.metaData.getString(key))
        }
    }

    private class MQTTBinder : Binder() {

    }

}
