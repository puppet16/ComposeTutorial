package cn.ltt.luck.compose.data

import android.app.Activity
import android.view.View
import cn.ltt.luck.compose.activity.BubbleTriangleActivity
import cn.ltt.luck.compose.activity.OfficialSampleActivity
import cn.ltt.luck.compose.activity.PaddingMarginActivity
import cn.ltt.luck.compose.widget.ToastUtil

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/4/28
 * desc    描述
 * ============================================================
 **/
class JumpActivityData {
    companion object {
        fun getJumpList(): List<JumpBean> = listOf(
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("内外间距示例", activityClass = PaddingMarginActivity::class.java),
            JumpBean("气泡三角", activityClass = BubbleTriangleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),
            JumpBean("点击示例", listener = { ToastUtil.short(text = "点击示例") }),
            JumpBean("官网示例", activityClass = OfficialSampleActivity::class.java),

        )

    }
}

data class JumpBean(
    val name: String,
    val listener: View.OnClickListener? = null,
    val activityClass: Class<out Activity>? = null
)