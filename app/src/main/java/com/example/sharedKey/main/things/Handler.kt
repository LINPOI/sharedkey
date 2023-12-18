package com.example.sharedKey.main.things

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import com.example.sharedKey.main.exitApp
import com.example.test1.R

@Composable
fun DoubleBackHandler(//雙擊滑鼠
    context:Context,
    enabled: Boolean = true,
    //onBackPressed: () -> Unit,

) {
    val duration = 2000 // 兩秒

    var backPressedTime by remember { mutableLongStateOf(0L) }

    BackHandler(enabled = enabled) {
        if (backPressedTime + duration > System.currentTimeMillis()) {

            exitApp(context)
        }else{
            showToast(context, R.string.press_back_again_to_exit)
            Logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/main/things/Handler.kt:31:24")
        }
        backPressedTime = System.currentTimeMillis()
    }
}
@Composable
fun HandleBackPress(onBackPressed: () -> Unit) {
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val backCallback = rememberUpdatedState(onBackPressed)

    val callback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backCallback.value()
            }
        }
    }

    DisposableEffect(dispatcher) {
        dispatcher?.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
}