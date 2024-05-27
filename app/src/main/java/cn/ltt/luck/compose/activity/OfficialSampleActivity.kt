package cn.ltt.luck.compose.activity

import Message
import SampleData
import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ltt.luck.compose.R
import cn.ltt.luck.compose.activity.base.BaseActivity
import cn.ltt.luck.compose.ui.theme.ComposeTutorialTheme
import cn.ltt.luck.compose.util.LogUtil

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/4/22
 * desc    官网示例：https://developer.android.google.cn/develop/ui/compose/tutorial?hl=zh-cn
 * ============================================================
 **/
class OfficialSampleActivity : BaseActivity() {

    @Composable
    override fun InitializeView() {
        Conversation(messages = SampleData.conversationSample)
    }


    /**
     * 一条消息
     * @param msg Message
     */
    @Composable
    fun MessageCard(msg: Message) {

        // 容器内间距8dp
        // 横向排列
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.mipmap.image16),
                contentDescription = "显示反派头像",
                // 图片大小40dp,且边框圆角8dp
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            // 图片与文本间距8dp
            Spacer(modifier = Modifier.width(8.dp))
            // 是否展开
            var isExpanded by remember { mutableStateOf(false) }

            // surfaceColor将从一种颜色逐渐更新到另一种颜色
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                label = "背景颜色"
            ) {
                LogUtil.d(TAG, "颜色$it")
            }
            // 纵向排列
            // 设置点击事件，点击将展开标志置反
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    modifier = Modifier.padding(8.dp, 0.dp),
                    text = msg.author,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium, shadowElevation = 2.dp,
                    color = surfaceColor,
                    // 将逐渐改变
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {

                    Text(
                        modifier = Modifier.padding(10.dp),
                        // 若展开则显示全部内容，否则只显示一行
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        text = msg.content,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewMessageCard() {
        ComposeTutorialTheme {
            Surface {
                MessageCard(Message("反派", "桀桀桀~ 还有什么手段尽可使来"))
            }
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) {
                MessageCard(msg = it)
            }
        }
    }

    @Preview(name = "消息会话")
    @Composable
    fun PreviewConversation() {
        ComposeTutorialTheme {
            Conversation(messages = SampleData.conversationSample)
        }
    }


}
