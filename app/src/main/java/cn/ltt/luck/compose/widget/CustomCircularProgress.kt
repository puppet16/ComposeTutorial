package cn.ltt.luck.compose.widget

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import cn.ltt.luck.compose.util.LogUtil
import kotlinx.coroutines.delay

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/5/28
 * desc    自定义圆形进度条
 * ============================================================
 **/

@Composable
fun CustomCircularProgress(
    // 是否开始走进度
    startProgress: Boolean = true,
    size: DpSize = DpSize(200.dp, 200.dp),
    trackColor: Color = Color.Gray,
    progressColor: Color = Color.Red,
    strokeWidth: Dp = 8.dp,
    // 进度条走多长时间，单位：毫秒
    totalDuration: Long = 5000,
    // 进度条回调，第一次参数：true，表示进度执行完成，第二个参数：为当前进度：进度[0,1.0f]
    callback: (Boolean, Float) -> Unit = { finished: Boolean, progress: Float -> }
) {
    val TAG = "CustomCircularProgress"
    LogUtil.d(TAG, "startProgress=$startProgress, size=$size, totalDuration=$totalDuration")
    // 总进度
    var progress by remember { mutableStateOf(0.0f) }

    // 多少毫秒更新一次进度
    val intervalMs = 50L
    // 更新一次要增加的进度
    val onceDuration = 1f / (totalDuration / intervalMs.toFloat())
    LogUtil.d(TAG, "更新间隔时间：$intervalMs, 每次增加的进度：$onceDuration")
    if (startProgress) {
        LaunchedEffect(Unit) {
            while (true) {
                if (progress < 1.0f) {
                    delay(intervalMs)
                    progress += onceDuration
                    callback.invoke(false, progress)
                } else {
                    callback.invoke(true, progress)
                    progress = 0F
                    break
                }
            }
        }
    }
    // 系统的圆形进度条工具
    CircularProgressIndicator(
        modifier = Modifier.size(size.width, size.height),
        progress = progress,
        color = progressColor,
        trackColor = trackColor,
        strokeCap = StrokeCap.Round,
        strokeWidth = strokeWidth
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(modifier = Modifier.size(500.dp, 500.dp)) {
        CustomCircularProgress()
    }
}
