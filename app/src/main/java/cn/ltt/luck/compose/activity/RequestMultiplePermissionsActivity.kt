package cn.ltt.luck.compose.activity

/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * https://google.github.io/accompanist/permissions/
 * https://github.com/google/accompanist/tree/main/sample/src/main/java/com/google/accompanist/sample/permissions
 */

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.ltt.luck.compose.activity.base.BaseActivity
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cn.ltt.luck.compose.widget.CheckPermissions
import cn.ltt.luck.compose.widget.FinishCurrentActivity
import cn.ltt.luck.compose.widget.ToastUtil

class RequestMultiplePermissionsActivity : BaseActivity() {

    @Composable
    override fun InitializeView() {
//        val multiplePermissionsState = rememberMultiplePermissionsState(
//            listOf(
//                android.Manifest.permission.RECORD_AUDIO,
//                android.Manifest.permission.READ_EXTERNAL_STORAGE,
//                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            )
//        )
//        Sample(multiplePermissionsState)
        CheckPermissions(
            listOf( android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            , @Composable { requestAction ->
//                Column {
//                    Text(text = "这个权限很关键，没有这个权限功能无法使用！")
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Button(onClick = { requestAction.invoke() }) {
//                        Text("同意")
//                    }
//                } }
        ) {
            if (it) {
                ToastUtil.long(text = "权限请求成功$it")
            } else {
                FinishCurrentActivity()
            }
        }
        Text(text = "这个权限很关键的啊，没有这个权限功能无法使用！")

    }


    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun Sample(multiplePermissionsState: MultiplePermissionsState) {
        if (multiplePermissionsState.allPermissionsGranted) {
            // If all permissions are granted, then show screen with the feature enabled
            Text("Record Audio and Read&Write storage permissions Granted! Thank you!")
        } else {
            Column {
                Text(
                    getTextToShowGivenPermissions(
                        multiplePermissionsState.revokedPermissions,
                        multiplePermissionsState.shouldShowRationale
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { multiplePermissionsState.launchMultiplePermissionRequest() }) {
                    Text("Request permissions")
                }
            }
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    private fun getTextToShowGivenPermissions(
        permissions: List<PermissionState>,
        shouldShowRationale: Boolean
    ): String {
        val revokedPermissionsSize = permissions.size
        if (revokedPermissionsSize == 0) return ""

        val textToShow = StringBuilder().apply {
            append("The ")
        }

        for (i in permissions.indices) {
            textToShow.append(permissions[i].permission)
            when {
                revokedPermissionsSize > 1 && i == revokedPermissionsSize - 2 -> {
                    textToShow.append(", and ")
                }

                i == revokedPermissionsSize - 1 -> {
                    textToShow.append(" ")
                }

                else -> {
                    textToShow.append(", ")
                }
            }
        }
        textToShow.append(if (revokedPermissionsSize == 1) "permission is" else "permissions are")
        textToShow.append(
            if (shouldShowRationale) {
                " important. Please grant all of them for the app to function properly."
            } else {
                " denied. The app cannot function without them."
            }
        )
        return textToShow.toString()
    }
}