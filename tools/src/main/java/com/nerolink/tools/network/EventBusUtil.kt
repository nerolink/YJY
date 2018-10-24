package com.nerolink.tools.network

import org.greenrobot.eventbus.EventBus

/**
 * Created by JJY on 2016/9/5.
 */
object EventBusUtil {
    var WhiteBoardEventBus = EventBus()
    var ExitWhiteBoardEventBus = EventBus()
    var CloseLoadingDialogEventBus = EventBus()
    var GroupDiscussEventBus = EventBus()
    var AllExamEventBus = EventBus()
    var VoteEventBus = EventBus()

    fun GetEventBus(type: String): EventBus {
       return when (type){
            "WhiteBoard"->WhiteBoardEventBus
            "ExitWhiteBoard"-> ExitWhiteBoardEventBus
            "CloseLoadingDialog"->CloseLoadingDialogEventBus
            "GroupDiscuss"->GroupDiscussEventBus
            "AllExamEventBus"-> AllExamEventBus
            "VoteEventBus"-> VoteEventBus
            else->EventBus.getDefault()
        }
    }
}
