package cn.ltt.luck.compose

import android.app.Application

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/4/28
 * desc    描述
 * ============================================================
 **/
class ComposeApplication: Application() {
    companion object {
        lateinit var instance: Application
    }
    init {
        instance = this
    }

}