package cn.ltt.luck.compose.activity.base

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import cn.ltt.luck.compose.ui.theme.ComposeTutorialTheme

/**
 * ============================================================
 *
 * @author 李桐桐
 * date    2024/4/29
 * desc    统一使用主题
 * ============================================================
 **/
abstract class BaseActivity: FragmentActivity() {
    val TAG = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                InitializeView()
            }
        }
    }
    @Composable
    protected abstract fun InitializeView()
}