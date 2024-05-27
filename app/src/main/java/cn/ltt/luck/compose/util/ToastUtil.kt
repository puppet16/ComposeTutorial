package cn.ltt.luck.compose.util

import android.content.Context
import android.widget.Toast
import cn.ltt.luck.compose.ComposeApplication

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/4/28
 * desc    描述
 * ============================================================
 **/
class ToastUtil {

    companion object {

        @JvmOverloads
        fun short(context: Context = ComposeApplication.instance.applicationContext, text: String) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
        @JvmOverloads
        fun long(context: Context = ComposeApplication.instance.applicationContext, text: String) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }
    }
}