package com.nerolink.resource_library.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.nerolink.resource_library.model.EventBusMessage
import com.nerolink.resource_library.network.MQTTClientInstance
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.greenrobot.eventbus.EventBus
import java.util.*

class MQTTService : Service() {

    private var isRunning:Boolean=false
    private val client=MQTTClientInstance.instance

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private fun init():Unit{
        isRunning = true
        client.setCallback(object:MqttCallback{
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun connectionLost(cause: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                EventBus.getDefault().post(EventBusMessage(EventBusMessage.EventBusMessageType.NETWORKOFFLINE))
                Timer().schedule(object:TimerTask(){
                    override fun run() {
                        println("reconnecting.....")

                    }
                },10,2000)

            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }


}
