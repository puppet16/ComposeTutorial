package cn.ltt.luck.compose.activity

import androidx.compose.runtime.Composable
import cn.ltt.luck.compose.activity.base.BaseActivity

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/5/10
 * desc    用贝塞尔曲线画的消息气泡上的三角小尾巴
 * ============================================================
 **/
class BubbleTriangleActivity  : BaseActivity() {

    @Composable
    override fun InitializeView() {
        BubbleTriangle()
    }
}