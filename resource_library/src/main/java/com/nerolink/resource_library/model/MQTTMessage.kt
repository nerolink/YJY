package com.nerolink.resource_library.model

/**
 * Created by JJY on 2016/8/22.
 */
class MQTTMessage {
    var type: Int = 0
    var content: String? = null
    var extra: String? = null
    var extra2: String? = null
    var extra3: String? = null
    var extra4: String? = null
    var extra5: String? = null
    var list: List<*>? = null


    constructor(type: Int) {
        this.type = type
    }

    constructor(type: Int, content: String) {
        this.type = type
        this.content = content
    }

    constructor(type: Int, content: String, extra: String) {
        this.type = type
        this.content = content
        this.extra = extra
    }

    constructor(type: Int, content: String, extra: String, extra2: String) {
        this.type = type
        this.content = content
        this.extra = extra
        this.extra2 = extra2
    }

    constructor(type: Int, list: List<*>, extra: String, content: String) {
        this.type = type
        this.list = list
        this.extra = extra
        this.content = content
    }

    object MQTTMessageType {
        val FILE = 0
        val ROLLCALL = 1
        val ROLLCALLBACK = 2
        val FILE_CLOUD = 3
        val WHITE_BOARD = 4
        val PICTURE_EXAM_SUBJECTIVE = 5
        val PICTURE_EXAM_CHOICE = 6
        val PICTURE_EXAM_CHOICE_MUL = 11
        val PICTURE_EXAM_CHOICE_OLD = 24
        val PICTURE_EXAM_SUBJECTIVE_OLD = 25

        val PICTURE_EXAM_SUBJECTIVE_GROUP = 9

        val PICTURE_EXAM_BACK_SUBJECTIVE = 7
        val PICTURE_EXAM_BACK_CHOICE = 8
        val PICTURE_EXAM_BACK_SUBJECTIVE_GROUP = 10
        val PICTURE_EXAM_BACK_CHOICE_MUL = 12
        val PICTURE_EXAM_SUBJECTIVE_GROUP_DISCUSS = 13
        val PICTURE_EXAM_FINISH = 15
        val PICTURE_EXAM_STUDENT_JUDGE = 16
        val PICTURE_EXAM_GROUP_JUDGE = 17
        val PICTURE_EXAM_TEACHER_JUDGE_BACK = 18
        val PICTURE_EXAM_TEACHER_GROUP_JUDGE_BACK = 19

        //组内互评
        val GROUP_EVALUATE = 22
        val GROUP_EVALUATE_BACK = 23

        //组间互评
        val GROUP_INTER_EVALUATE = 32

        //开始、结束上课
        val START_CLASS = 26
        val STOP_CLASS = 27

        //分享截图
        val SHARE_PICTURE = 28

        val START_VOTE = 29
        val STOP_VOTE = 30
        val RESULT_VOTE = 31


    }

    override fun toString(): String {
        return "MQTTMessage{" +
                "type=" + type +
                ", content='" + content + '\''.toString() +
                ", extra='" + extra + '\''.toString() +
                ", extra2='" + extra2 + '\''.toString() +
                ", extra3='" + extra3 + '\''.toString() +
                ", extra4='" + extra4 + '\''.toString() +
                ", extra5='" + extra5 + '\''.toString() +
                ", list=" + list +
                '}'.toString()
    }
}
