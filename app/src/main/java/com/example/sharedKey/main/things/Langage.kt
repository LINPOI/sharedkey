package com.example.sharedKey.main.things

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import java.util.Locale

@Composable
fun nowLangage():String {
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/main/things/Langage.kt:10:16")
    val currentLocale: Locale = LocalConfiguration.current.locales[0]
    val currentLanguage: String = currentLocale.language // 取得當前語言代碼
    val currentDisplayName: String = currentLocale.displayName // 取得當前語言的顯示名稱
    Log.i("AvailableLanguage", "Language:$currentLanguage name:$currentDisplayName")
    return  currentLanguage

}
@Composable
fun isChinese():Boolean {
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/main/things/Langage.kt:20:16")
    val currentLocale: Locale = LocalConfiguration.current.locales[0]
    val currentLanguage: String = currentLocale.language // 取得當前語言代碼
    val currentDisplayName: String = currentLocale.displayName // 取得當前語言的顯示名稱
    Log.i("AvailableLanguage", "Language:$currentLanguage name:$currentDisplayName")
    return currentDisplayName=="zh"
}