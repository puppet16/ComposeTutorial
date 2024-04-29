package cn.ltt.luck.compose.activity

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.ltt.luck.compose.activity.base.BaseActivity
import cn.ltt.luck.compose.data.JumpActivityData
import cn.ltt.luck.compose.data.JumpBean
import cn.ltt.luck.compose.ui.theme.BackGroundColor
import cn.ltt.luck.compose.ui.theme.ContentColor
/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/4/22
 * desc    程序入口，各示例入口
 * ============================================================
 **/
class MainActivity: BaseActivity() {

    // 一行最多四个按钮
    private val MAX_COL = 4

    @Composable
    override fun InitializeView() {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            JumpButtonList(list = JumpActivityData.getJumpList())
        }
    }

    /**
     * 整体列表
     * @param list List<JumpBean>
     */
    @Composable
    fun JumpButtonList(list: List<JumpBean>) {
        LazyVerticalGrid(columns = GridCells.Fixed(MAX_COL)) {
            items(JumpActivityData.getJumpList().size) {
                if (it < list.size) {
                    JumpButton(bean = list[it])
                }
            }
        }
    }

    /**
     * 列表的 item
     * @param bean JumpBean
     */
    @Composable
    fun JumpButton(bean: JumpBean) {
        Button(
            onClick = { onButtonClick(bean) },
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.buttonColors(
                containerColor = BackGroundColor,
                contentColor = ContentColor
            ),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = bean.name,
                fontSize = 25.sp,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )
        }
    }


    @Preview
    @Composable
    fun PreviewJumpButtonList() {
        JumpButtonList(list = JumpActivityData.getJumpList())
    }

    /**
     * item 单击事件处理
     * @param bean JumpBean
     */
    private fun onButtonClick(bean: JumpBean) {
        if (bean.listener != null) {
            bean.listener.onClick(null)
        } else if (bean.activityClass != null) {
            startActivity(Intent(this, bean.activityClass))
        }
    }
}
