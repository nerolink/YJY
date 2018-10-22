package com.nerolink.resource_library.model

/**
 * Created by JJY on 2016/8/22.
 */
class EventBusMessage {
    var type: Int = 0
    var content: String? = null
    var mqttMessage: MQTTMessage? = null

    constructor(type: Int) {
        this.type = type
    }

    constructor(type: Int, content: String) {
        this.type = type
        this.content = content
    }

    constructor(type: Int, mqttMessage: MQTTMessage) {
        this.type = type
        this.mqttMessage = mqttMessage
    }

    object EventBusMessageType {
        val LOGINMQTT = 0
        val ROLLCALLBACK = 1
        val PICANSWERBACK = 2
        val CHOICEANSWERBACK = 3
        val CHOICEANSWERBACKMUL = 4
        val NETWORKOFFLINE = 5
        val NETWORKONLINE = 6
        val RESULTVOTE = 7
    }
}
