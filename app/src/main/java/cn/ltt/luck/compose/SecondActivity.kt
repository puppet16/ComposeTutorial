package cn.ltt.luck.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/4/22
 * desc    描述
 * ============================================================
 **/
class SecondActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smile(name = "反派")
        }
    }

    @Composable
    fun Smile(name: String) {
        Text(text = "${name}发出一阵笑声：桀桀桀~")
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewSmile() {
        Smile(name = "反派")
    }

}