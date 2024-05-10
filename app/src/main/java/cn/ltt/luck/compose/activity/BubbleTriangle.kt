package cn.ltt.luck.compose.activity

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ltt.luck.compose.ui.theme.ComposeTutorialTheme
import cn.ltt.luck.compose.widget.LogUtil
import kotlin.math.max
import kotlin.math.min

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/5/10
 * desc    消息气泡上的三角小尾巴
 * ============================================================
 **/

@Composable
fun BubbleTriangle(
    modifier: Modifier = Modifier,
    triangleColor: Color = Color.LightGray,
    rectWidthDp: Int = 80,// 包裹三角的矩形的宽度
    rectHeightDp: Int = 80,// 包裹三角的矩形的高度
    lineSizeDp: Float = rectWidthDp / 2f, // 三角横直线的大小
    location: TriangleLocation = TriangleLocation.BOTTOM_LEFT,
) {
    lineSizeDp.checkInRange(0f, rectWidthDp.toFloat())

    Box {
        // 绘制带有弧度的三角形
        Canvas(modifier = Modifier.size(rectWidthDp.dp, rectHeightDp.dp)) {
            LogUtil.d(msg="rectWidth=$rectWidthDp, rectHeight=$rectHeightDp, lineSize=${lineSizeDp.dp.toPx()}, 实际大小：width=${size.width}, height=${size.height}")
            val path = Path()
            val leftPointF = BubbleTriangle.calLeftPointF(size, lineSizeDp.dp.toPx(), location)
            val rightPointF = BubbleTriangle.calRightPointF(size, lineSizeDp.dp.toPx(), location)
            val tailPointF = BubbleTriangle.calTailPointF(size, lineSizeDp.dp.toPx(), location)
            // 移动到三角形横线左侧顶点位置
            path.moveTo(leftPointF.x, leftPointF.y)
            // 画三角形的横线，画到横线右侧顶点
            path.lineTo(rightPointF.x, rightPointF.y)
            // 画横线右侧顶点到尾巴顶点的曲线
            // 先计算贝塞尔曲线的控制点
            val rightLineControlPoint =
                BubbleTriangle.calculateBezierControlPoint(rightPointF, tailPointF, location)
            // 画曲线
            path.quadraticBezierTo(
                rightLineControlPoint.x, rightLineControlPoint.y,
                tailPointF.x, tailPointF.y
            )
            // 画从尾巴顶点到横线左侧顶点的曲线
            // 先计算贝塞尔曲线的控制点
            val leftLineControlPoint = BubbleTriangle.calculateBezierControlPoint(
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
//            // 绘制阴影效果
//            drawIntoCanvas { canvas ->
//                canvas.save()
//                canvas.translate(2.dp.toPx(), 2.dp.toPx()) // 设置阴影偏移量
//                canvas.drawPath(path, Paint().apply {
//                    color = Color.Gray
//                    style = androidx.compose.ui.graphics.PaintingStyle.Stroke
//                    strokeWidth = 20.dp.toPx() // 阴影宽度
//                })
//                canvas.restore()
//            }
            // 绘制实际路径
            drawPath(path = path, color = triangleColor)
        }
    }
}

/**
 * 气泡三角相对于气泡的位置
 */
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

fun Float.checkInRange(min: Float, max: Float) {
    require(this in min..max) { "Number must be in range $min..$max" }
}

class BubbleTriangle {
    companion object {
        /**
         * 计算横线左侧顶点坐标
         * @param rectSize Size
         * @param lineSize Float
         * @param location TriangleLocation
         * @return PointF
         */
        fun calLeftPointF(rectSize: Size, lineSize: Float, location: TriangleLocation): PointF {
            return when (location) {
                TriangleLocation.BOTTOM_LEFT -> {
                    PointF(rectSize.width - lineSize, 0f)
                }

                TriangleLocation.BOTTOM_RIGHT -> {
                    PointF(0f, 0f)
                }

                TriangleLocation.TOP_LEFT -> {
                    PointF(rectSize.width - lineSize, rectSize.height)
                }

                TriangleLocation.TOP_RIGHT -> {
                    PointF(0f, rectSize.height)
                }
            }
        }

        /**
         * 计算横线右侧顶点坐标
         * @param rectSize Size
         * @param lineSize Float
         * @param location TriangleLocation
         * @return PointF
         */
        fun calRightPointF(rectSize: Size, lineSize: Float, location: TriangleLocation): PointF {
            return when (location) {
                TriangleLocation.BOTTOM_LEFT -> {
                    PointF(rectSize.width, 0f)
                }

                TriangleLocation.BOTTOM_RIGHT -> {
                    PointF(lineSize, 0f)
                }

                TriangleLocation.TOP_LEFT -> {
                    PointF(rectSize.width, rectSize.height)
                }

                TriangleLocation.TOP_RIGHT -> {
                    PointF(lineSize, rectSize.height)
                }
            }

        }

        /**
         * 根据位置计算尾巴顶点坐标
         * @param rectSize Size
         * @param lineSize Float
         * @param location TriangleLocation
         * @return PointF
         */
        fun calTailPointF(rectSize: Size, lineSize: Float, location: TriangleLocation): PointF {
            return when (location) {
                TriangleLocation.BOTTOM_LEFT -> {
                    PointF(0f, rectSize.height)
                }

                TriangleLocation.BOTTOM_RIGHT -> {
                    PointF(rectSize.width, rectSize.height)
                }

                TriangleLocation.TOP_LEFT -> {
                    PointF(0f, 0f)
                }

                TriangleLocation.TOP_RIGHT -> {
                    PointF(rectSize.width, 0f)
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

    }
}


@Preview
@Composable
fun PreviewNormalBottomLeft() {
    ComposeTutorialTheme {
        BubbleTriangle(location = TriangleLocation.BOTTOM_LEFT)
    }
}

@Preview
@Composable
fun PreviewNormalBottomRight() {
    ComposeTutorialTheme {
        BubbleTriangle(location = TriangleLocation.BOTTOM_RIGHT)
    }
}

@Preview
@Composable
fun PreviewNormalTopLeft() {
    ComposeTutorialTheme {
        BubbleTriangle(location = TriangleLocation.TOP_LEFT)
    }
}

@Preview
@Composable
fun PreviewNormalTopRight() {
    ComposeTutorialTheme {
        BubbleTriangle(location = TriangleLocation.TOP_RIGHT)
    }
}
