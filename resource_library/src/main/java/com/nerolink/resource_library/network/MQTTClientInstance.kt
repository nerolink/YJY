package com.nerolink.resource_library.network

import com.nerolink.resource_library.constant.APPConstant
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import java.lang.Exception

object MQTTClientInstance {

    val instance: MqttClient by lazy { MqttClient(APPConstant.MQTTHOST, MqttClient.generateClientId(), MemoryPersistence()) }
    val option: MqttConnectOptions by lazy {
        var o = MqttConnectOptions()

        o.isCleanSession = true
        //设置连接的用户名
        o.userName = APPConstant.MQTTHOST
        //设置连接的密码
        o.password = APPConstant.MQTTPASSWORD.toCharArray()
        // 设置超时时间 单位为秒
        o.connectionTimeout = 10
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        o.keepAliveInterval = 20
        return@lazy o
    }

    fun sendMessage(content: String, myTopic: String) {
        val topic = instance.getTopic(myTopic)
        val message = MqttMessage()
        message.qos = 1
        message.isRetained = false
        message.payload = content.toByteArray()
        try {
            topic.publish(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sendMessage(content: String, topics: List<String>) {
        topics.forEach { t -> sendMessage(content, t) }
    }

    fun sendMessage(content: String, topics: Array<String>) {
        topics.forEach { t -> sendMessage(content, t) }
    }

    fun connect
}