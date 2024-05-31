package cn.ltt.luck.compose.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.ltt.luck.compose.activity.base.BaseActivity
import cn.ltt.luck.compose.util.LogUtil
import cn.ltt.luck.compose.widget.CustomCircularProgress

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/5/31
 * desc    描述
 * ============================================================
 **/
class CircularProgressActivity : BaseActivity() {

    @Composable
    override fun InitializeView() {

        ShowProgress()
    }

    @Composable
    fun ShowProgress() {
        var start by remember {
            mutableStateOf(false)
        }
        var show by remember {
            mutableStateOf(true)
        }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { start = true }) {
                    Text(text = "开始进度条")
                }

                Spacer(modifier = Modifier.size(80.dp, 80.dp))
                Button(onClick = { start = false }) {
                    Text(text = "停止进度条")
                }

                Spacer(modifier = Modifier.size(80.dp, 80.dp))
                Button(onClick = { show = true }) {
                    Text(text = "显示进度条")
                }

                Spacer(modifier = Modifier.size(80.dp, 80.dp))
                Button(onClick = { show = false }) {
                    Text(text = "隐藏进度条")
                }
            }

            Spacer(modifier = Modifier.size(80.dp, 80.dp))
            Box(modifier = Modifier.size(500.dp, 500.dp), contentAlignment = Alignment.Center) {
            if (show) {
                CustomCircularProgress(start, size = DpSize(500.dp, 500.dp)) { finished, progress ->
                    LogUtil.d("CustomCircularProgress", "是否结束：$finished, 进度：$progress")
                    if (finished) {
                        show = false
                    }
                }
            }
                Text(text = "圆形进度条", fontSize = 32.sp)
            }
        }
    }

    @Preview
    @Composable
    fun ShowProgressDemo() {
        ShowProgress()
    }

}