package cn.ltt.luck.compose.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.ltt.luck.compose.activity.base.BaseActivity
import cn.ltt.luck.compose.ui.theme.DarkGrayColor
import cn.ltt.luck.compose.ui.theme.GrayColor
import cn.ltt.luck.compose.ui.theme.LightGrayColor
import cn.ltt.luck.compose.ui.theme.StressColor

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/4/29
 * desc    Compose 模式下内间距和外间距如何设置
 * ============================================================
 **/
class PaddingMarginActivity : BaseActivity() {

    @Composable
    override fun InitializeView() {
        RootView()
    }

    @Preview
    @Composable
    fun RootView() {
        val scrollState = rememberScrollState()

        val annotatedString = buildAnnotatedString {
            append("在")
            ApplyEnglishStyle("Compose")
            append("中，")
            ApplyEnglishStyle("Margin")
            append("和")
            ApplyEnglishStyle("Padding")
            append("都用")
            ApplyEnglishStyle("Modifier.padding()")
            append("来设置。 ")
            ApplyEnglishStyle("padding")
            append("和")
            ApplyEnglishStyle("margin")
            append("的区别最主要的就是")
            withStyle(
                style = SpanStyle(
                    color = DarkGrayColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W900
                )
            ) {
                append("在于背景色的设置。")
            }
        }

        Column(
            Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Text(text = "在 Compose 中，Modifier 的调用顺序是有影响的。后设置的参数会对之前设置的参数产生影响。", style = stressTextStyle)
            Text(text = "比如：先设置padding再设置背景，则背景只会绘制在padding之后的区域，变相实现了margin", style = stressTextStyle)

            Text(text = annotatedString, style = normalTextStyle)
            Spacer(modifier = Modifier.size(20.dp, 20.dp))
            Box(Modifier.background(LightGrayColor)) {
                Column {
                    Text(text = "正常什么都不设置的样式", style = stressTextStyle)
                    Text(
                        text = "只设置背景", modifier = Modifier
                            .background(Color.Green), style = normalTextStyle
                    )
                    Spacer(modifier = Modifier.size(10.dp, 10.dp))
                    Text(
                        text = "设置背景，且占满宽度", modifier = Modifier
                            .background(Color.Green)
                            .fillMaxWidth(), style = normalTextStyle
                    )
                    Spacer(modifier = Modifier.size(10.dp, 10.dp))

                    Text(
                        text = "只设置padding", modifier = Modifier
                            .padding(20.dp), style = normalTextStyle
                    )

                    Spacer(modifier = Modifier.size(10.dp, 10.dp))

                    Text(
                        text = "背景色包括 padding 的部分，效果类似 padding",
                        style = stressTextStyle
                    )
                    Text(
                        text = "设置背景，后设置padding", modifier = Modifier
                            .background(Color.Green)
                            .padding(20.dp), style = normalTextStyle
                    )

                    Text(
                        text = "背景色不包括 padding 的部分，效果类似 margin",
                        style = stressTextStyle
                    )

                    Text(
                        text = "设置padding，后设置背景", modifier = Modifier
                            .padding(20.dp)
                            .background(Color.Green), style = normalTextStyle
                    )
                    Text(text = "同时设置了 padding 和 margin 的效果", style = stressTextStyle)

                    Text(
                        text = "先设置padding 再设置背景色，最后再设置padding", modifier = Modifier
                            .padding(20.dp)
                            .background(Color.Green)
                            .padding(20.dp), style = normalTextStyle
                    )
                    Spacer(modifier = Modifier.size(30.dp, 30.dp))
                    Text(text = "再强调一遍，Modifier 的调用顺序是有影响的。后设置的参数会对之前设置的参数产生影响。(用力敲黑板)", style = stressTextStyle)

                }
            }

        }
    }

    @Composable
    fun AnnotatedString.Builder.ApplyEnglishStyle(content: String) {
        withStyle(
            style = SpanStyle(
                color = GrayColor,
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic,
            )
        ) {
            append(content)
        }
    }

    private val normalTextStyle = TextStyle(
        color = DarkGrayColor,
        fontSize = 24.sp
    )

    private val stressTextStyle = TextStyle(
        color = StressColor,
        fontSize = 26.sp,
        fontWeight = FontWeight.Black
    )

}