package cn.ltt.luck.compose.activity

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ltt.luck.compose.ui.theme.ComposeTutorialTheme
import kotlin.math.max
import kotlin.math.min

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/5/10
 * desc    描述
 * ============================================================
 **/

@Composable
fun CustomTriangleDialog(
    modifier: Modifier = Modifier,
    triangleColor: Color = MaterialTheme.colorScheme.primary,
    rectWidth: Int = 80,
    rectHeight: Int = 80,
    lineDistance: Int = rectWidth / 2,
    triangleOffsetX: Int = 0, // 三角形相对于对话框中心的水平偏移量
    triangleOffsetY: Int = 0, // 三角形相对于对话框中心的垂直偏移量
    location: TriangleLocation = TriangleLocation.BOTTOM_LEFT,
    content: @Composable () -> Unit
) {
    lineDistance.checkInRange(0, rectWidth)

    Box(
        modifier = Modifier.offset(
            x = triangleOffsetX.dp,
            y = triangleOffsetY.dp
        )
    ) {
        // 绘制对话框的内容
        content()

        // 绘制带有弧度的三角形
        Canvas(modifier = Modifier.size(rectWidth.dp, rectHeight.dp)) {
            val path = Path()
            val leftPointF = calLeftPointF(size, lineDistance, location)
            val rightPointF = calRightPointF(size, lineDistance, location)
            val tailPointF = calTailPointF(size, lineDistance, location)
            // 移动到三角形横线左侧顶点位置
            path.moveTo(leftPointF.x, leftPointF.y)
            // 画三角形的横线，画到横线右侧顶点
            path.lineTo(rightPointF.x, rightPointF.y)
            // 画横线右侧顶点到尾巴顶点的曲线
            // 先计算贝塞尔曲线的控制点
            val rightLineControlPoint =
                calculateBezierControlPoint(rightPointF, tailPointF, location)
            // 画曲线
            path.quadraticBezierTo(
                rightLineControlPoint.x, rightLineControlPoint.y,
                tailPointF.x, tailPointF.y
            )
            // 画从尾巴顶点到横线左侧顶点的曲线
            // 先计算贝塞尔曲线的控制点
            val leftLineControlPoint = calculateBezierControlPoint(
                tailPointF,
                leftPointF,
                location
            )
            // 画曲线
            path.quadraticBezierTo(
                leftLineControlPoint.x,
                leftLineControlPoint.y,
                leftPointF.x,
                leftPointF.y
            )

            drawPath(path = path, color = triangleColor)
        }
    }


}

enum class TriangleLocation {
    /**
     * 气泡的上部偏左
     */
    TOP_LEFT,

    /**
     * 气泡的上部偏右
     */
    TOP_RIGHT,

    /**
     * 气泡的底部偏左
     */
    BOTTOM_LEFT,

    /**
     * 气泡的底部偏右
     */
    BOTTOM_RIGHT
}

fun Int.checkInRange(min: Int, max: Int) {
    require(this in min..max) { "Number must be in range $min..$max" }
}

fun calLeftPointF(rectSize: Size, lineDistance: Int, location: TriangleLocation): PointF {
    return when (location) {
        TriangleLocation.BOTTOM_LEFT -> {
            PointF(rectSize.width / 2f, 0f)
        }

        TriangleLocation.BOTTOM_RIGHT -> {
            PointF(0f, 0f)
        }

        TriangleLocation.TOP_LEFT -> {
            PointF(rectSize.width / 2f, rectSize.height)
        }

        TriangleLocation.TOP_RIGHT -> {
            PointF(0f, rectSize.height)
        }
    }
}

fun calRightPointF(size: Size, lineDistance: Int, location: TriangleLocation): PointF {
    return when (location) {
        TriangleLocation.BOTTOM_LEFT -> {
            PointF(size.width, 0f)
        }

        TriangleLocation.BOTTOM_RIGHT -> {
            PointF(size.width / 2f, 0f)
        }

        TriangleLocation.TOP_LEFT -> {
            PointF(size.width, size.height)
        }

        TriangleLocation.TOP_RIGHT -> {
            PointF(size.width / 2f, size.height)
        }
    }

}

fun calTailPointF(size: Size, lineDistance: Int, location: TriangleLocation): PointF {
    return when (location) {
        TriangleLocation.BOTTOM_LEFT -> {
            PointF(0f, size.height)
        }

        TriangleLocation.BOTTOM_RIGHT -> {
            PointF(size.width, size.height)
        }

        TriangleLocation.TOP_LEFT -> {
            PointF(0f, 0f)
        }

        TriangleLocation.TOP_RIGHT -> {
            PointF(size.width, 0f)
        }
    }
}

/**
 * 计算贝塞尔曲线的控制点坐标
 * @param start PointF
 * @param end PointF
 * @param location TriangleLocation
 * @return PointF
 */
fun calculateBezierControlPoint(
    start: PointF,
    end: PointF,
    location: TriangleLocation
): PointF {
    // 计算控制点坐标
    val controlY = max(start.y, end.y) / 2f
    val controlX = if (location == TriangleLocation.TOP_RIGHT || location == TriangleLocation.BOTTOM_RIGHT) {
        min(start.x, end.x)
    } else {
        max(start.x, end.x)
    }
    return PointF(controlX, controlY)
}

@Preview
@Composable
fun PreviewNormalBottomLeft() {
    ComposeTutorialTheme {
        CustomTriangleDialog(location = TriangleLocation.BOTTOM_LEFT) {

        }
    }
}

@Preview
@Composable
fun PreviewNormalBottomRight() {
    ComposeTutorialTheme {
        CustomTriangleDialog(location = TriangleLocation.BOTTOM_RIGHT) {

        }
    }
}

@Preview
@Composable
fun PreviewNormalTopLeft() {
    ComposeTutorialTheme {
        CustomTriangleDialog(location = TriangleLocation.TOP_LEFT) {

        }
    }
}

@Preview
@Composable
fun PreviewNormalTopRight() {
    ComposeTutorialTheme {
        CustomTriangleDialog(location = TriangleLocation.TOP_RIGHT) {

        }
    }
}
