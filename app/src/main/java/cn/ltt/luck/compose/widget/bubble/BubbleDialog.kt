package cn.ltt.luck.compose.widget.bubble

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import cn.ltt.luck.compose.ui.theme.bubbleBg
import cn.ltt.luck.compose.ui.theme.bubbleTextStyle
import cn.ltt.luck.compose.widget.bubble.BubbleDialog.Companion.BUBBLE_RECT
import cn.ltt.luck.compose.widget.bubble.BubbleDialog.Companion.BUBBLE_TRIANGLE
import cn.ltt.luck.compose.widget.bubble.BubbleDialog.Companion.decoupledConstraints

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/5/13
 * desc    气泡对话框
 * ============================================================
 **/

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun BubbleDialog(
    modifier: Modifier = Modifier,
    // 气泡背景色
    bgColor: Color = bubbleBg,
    // 气泡三角小尾巴的位置
    triangleLocation: TriangleLocation = TriangleLocation.BOTTOM_LEFT,
    // 气泡文本内容
    content: String = "这是个测试文本",
    // 气泡里文本的样式
    textStyle: TextStyle = bubbleTextStyle
) {

    val shape = RoundedCornerShape(12.0.dp)
    BoxWithConstraints(
        modifier = Modifier
            .background(Color.Transparent)
            .then(modifier)) {
        // 根据小尾巴位置，计算矩形与小尾巴的约束条件
        val constraints = decoupledConstraints(triangleLocation)

        ConstraintLayout(
            constraints,
            modifier = Modifier
                .background(Color.Transparent)
                .then(modifier)
        ) {
            // 气泡矩形
            Box(modifier = Modifier
                .layoutId(BUBBLE_RECT)
                .drawBehind {
                    // 绘制对话框圆角矩形的阴影
                    drawIntoCanvas { canvas ->
                        canvas.save()
                        canvas.translate(1.dp.toPx(), 1.dp.toPx()) // 设置阴影偏移量
                        drawRoundRect(
                            color = bgColor.copy(alpha = 0.2F),
                            size = Size(size.width, size.height),
                            style = Stroke(2.dp.toPx()),
                            cornerRadius = CornerRadius(12.dp.toPx(), 12.dp.toPx())
                        )
                        canvas.restore()
                    }
                }
                .background(bgColor, shape = shape)
                .padding(20.dp)) {
                // 气泡文本
                Text(text = content, style = textStyle)
            }
            // 气泡三角小尾巴
            Surface(
                modifier = Modifier
                    .layoutId(BUBBLE_TRIANGLE)
                    .zIndex(-1F)
            ) {
                BubbleTriangle(
                    triangleColor = bubbleBg,
                    location = triangleLocation,
                    rectHeightDp = 50
                )
            }
        }

    }
}

class BubbleDialog {
    companion object {
        const val BUBBLE_RECT = "bubbleRect"
        const val BUBBLE_TRIANGLE = "bubbleTriangle"

        /**
         * 根据小尾巴不同位置，返回不同的矩形和小尾巴的约束条件
         * @param triangleLocation TriangleLocation
         * @return ConstraintSet
         */
        fun decoupledConstraints(triangleLocation: TriangleLocation): ConstraintSet {
            // 小尾巴与矩形相交的区域高度
            val marginDp = (-7).dp

            return ConstraintSet {
                val bubbleRect = createRefFor(BUBBLE_RECT)
                val bubbleTriangle = createRefFor(BUBBLE_TRIANGLE)

                when(triangleLocation) {
                    TriangleLocation.TOP_LEFT -> {
                        constrain(bubbleRect) {
                            top.linkTo(bubbleTriangle.bottom, marginDp)
                        }
                        constrain(bubbleTriangle) {
                            top.linkTo(parent.top)
                        }
                    }

                    TriangleLocation.TOP_RIGHT -> {
                        constrain(bubbleRect) {
                            top.linkTo(bubbleTriangle.bottom, marginDp)
                        }
                        constrain(bubbleTriangle) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                    }

                    TriangleLocation.BOTTOM_LEFT -> {
                        constrain(bubbleRect) {
                            top.linkTo(parent.top)
                        }
                        constrain(bubbleTriangle) {
                            top.linkTo(bubbleRect.bottom, marginDp)
                        }
                    }

                    TriangleLocation.BOTTOM_RIGHT -> {
                        constrain(bubbleRect) {
                            top.linkTo(parent.top)
                        }
                        constrain(bubbleTriangle) {
                            top.linkTo(bubbleRect.bottom, marginDp)
                            end.linkTo(bubbleRect.end)
                        }
                    }
                }

            }
        }
    }
}


@Preview
@Composable
fun PreviewTop_Left() {
    Surface {
        BubbleDialog(triangleLocation = TriangleLocation.TOP_LEFT)
    }
}

@Preview
@Composable
fun PreviewTop_Right() {
    Surface {
        BubbleDialog(triangleLocation = TriangleLocation.TOP_RIGHT)
    }
}

@Preview
@Composable
fun PreviewBottom_Left() {
    Surface {
        BubbleDialog(triangleLocation = TriangleLocation.BOTTOM_LEFT)
    }
}

@Preview
@Composable
fun PreviewBottom_Right() {
    Surface {
        BubbleDialog(triangleLocation = TriangleLocation.BOTTOM_RIGHT)
    }
}
