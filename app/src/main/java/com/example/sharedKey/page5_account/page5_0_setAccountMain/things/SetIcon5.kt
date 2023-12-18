package com.example.sharedKey.page5_account.page5_0_setAccountMain.things

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.test1.R

@SuppressLint("SuspiciousIndentation")
@Composable
fun SetIcon(){
    val isDarkMode = isSystemInDarkTheme()
// 在暗色模式下的樣式
    Image(
        painter = painterResource(id = R.drawable.avatar),
        contentDescription = "Avatar",
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    // 點擊時回應
                    Log.i(
                        "0123",
                        "File path: C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_0_setAccountMain/things/SetIcon5.kt:35:175"
                    )
                }
            },
        contentScale = ContentScale.FillHeight,
        colorFilter = if (isDarkMode) ColorFilter.tint(Color.White) else ColorFilter.tint(Color.Black)
    )

}