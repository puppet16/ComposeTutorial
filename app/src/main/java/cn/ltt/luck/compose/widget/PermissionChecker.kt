package cn.ltt.luck.compose.widget

import androidx.activity.ComponentActivity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


/**
 * 校验传入的权限列表
 * 有权限，会回调 函数@result，传入值为 true
 * 无权限，再判断是否需要阐述申请理由，不需要则直接申请权限
 * 需要阐述理由，则显示传入的阐述理由UI @showRationale
 * 请求权限后的结果会通过 @result 进行回调
 *
 * @permissions 需要进行校验的权限列表
 * @showRationale 需要阐述理由的UI展示，可自定义，默认显示一个弹窗，弹窗点确定会请求一次权限，点取消会关闭当前页面
 * @result 回调有无权限：true,有权限; false,无权限
 *
 * 若无权限会请求权限
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckPermissions(permissions: List<String>,
                     showRationale: @Composable (@Composable ()->Unit) ->Unit = { PermissionRationaleDialog(sureListener = it) },
                     result: @Composable (Boolean) -> Unit) {
    // 是否检查请求权限的结果
    var checkRequestResult by remember {
        mutableStateOf(false to false)
    }
    val multiplePermissionsState = rememberMultiplePermissionsState(
        permissions
    ) {
        var isAllGranted = true
        it.keys.forEach {permissionStr ->
            if (it[permissionStr] == false) {
                LogUtil.d(msg = "请求权限结果：${it[permissionStr]}  $permissionStr")
                isAllGranted = false
            }
        }
        checkRequestResult = true to isAllGranted
    }
    if (checkRequestResult.first) {
        LogUtil.d(msg = "回调全部权限请求权限最终结果：${checkRequestResult.second}")
        result.invoke(checkRequestResult.second)
        return
    }
    LogUtil.d(msg = "校验权限 permissions=$permissions")
    if (multiplePermissionsState.allPermissionsGranted) {
        LogUtil.d(msg = "有权限 permissions=$permissions")
        result.invoke(true)
    } else {
        if (multiplePermissionsState.shouldShowRationale) {
            // 需要显示申请权限的理由，一般弹窗显示，并向其传输真正向系统请求权限的函数
            LogUtil.d(msg = "无权限,需要阐述权限理由 permissions=$permissions")
            showRationale.invoke { multiplePermissionsState.launchMultiplePermissionRequest() }
        } else {
            // 直接向系统请求权限
            LogUtil.d(msg = "向系统请求权限：permissions=$permissions")
            run { multiplePermissionsState.launchMultiplePermissionRequest() }
        }
    }
}

/**
 * 申请权限时需要阐述理由时显示的弹窗
 * @content 阐述的理由
 * @sureListener 点击确定按钮的回调
 * @cancelListener 点击取消按钮的回调，默认关闭当前页面
 */
@Composable
private fun PermissionRationaleDialog(content: String = "这个权限很关键，没有这个权限功能无法使用，且会返回上一个页面！",
                                      sureListener: @Composable () -> Unit = {},
                                      cancelListener: @Composable () -> Unit = { FinishCurrentActivity() }) {
    // 用于判断是否显示弹窗，改变值会触发重组
    var showDialog by remember { mutableStateOf(true) }
    // 用于判断是否执行取消回调，因取消回调内的默认实现使用了组合项里的函数，所以要在组合项内调用
    var invokeCancelListener by remember {
        mutableStateOf(false)
    }
    // 用于判断是否执行取消回调，因取消回调内的默认实现使用了组合项里的函数，所以要在组合项内调用
    var invokeSureListener by remember {
        mutableStateOf(false)
    }
    if (invokeCancelListener) {
        cancelListener.invoke()
    }
    if (invokeSureListener) {
        sureListener.invoke()
    }

    if (!showDialog) return

    AlertDialog(
        onDismissRequest = {
            // 当用户点击对话框以外的区域或返回键时，执行此方法
            showDialog = true
        },
        title = {
            Text(text = "权限申请原因")
        },
        text = {
            Text(text = content)
        },
        confirmButton = {
            Button(onClick = {
                invokeSureListener = true
                showDialog = false
            }) {
                Text("确定")
            }
        },
        dismissButton = {
            Button(onClick = {
                invokeCancelListener = true
                showDialog = false
            }) {
                Text("取消")
            }
        }
    )
}

/**
 * 关闭当前页面
 */
@Composable
fun FinishCurrentActivity(){
    val context = LocalContext.current
    if (context is ComponentActivity) {
        context.finish()
    }
}
