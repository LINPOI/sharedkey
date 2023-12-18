package com.example.sharedKey.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.sharedKey.ui.theme.Theme

//導入子資料夾



private var lastToast: Toast? = null// 記錄上一個 Toast 的實例，以便取消
//初始設置
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                AppNavigator()

            }
        }
    }
}

fun exitApp(context: Context) {
    lastToast?.cancel()
    (context as? Activity)?.finish()
    System.exit(0)
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Theme {
        AppNavigator()
    }
}
