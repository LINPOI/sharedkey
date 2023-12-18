package com.example.sharedKey.page4_message

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.sharedKey.main.things.DoubleBackHandler
import com.example.sharedKey.main.things.logaddress
import com.example.test1.R

@Composable
fun SetMessage(navController: NavController,//分頁導入
               modifier: Modifier = Modifier
){
    LazyColumn(
        modifier,
        verticalArrangement = Arrangement.Center,//水平(左右)
        horizontalAlignment = Alignment.CenterHorizontally//垂直(上下)
    ){
        logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page4_message/SetMessage.kt:26:20","SetMessage")
        items(1) {
            Text(text = stringResource(R.string.message) )
        }
    }
    DoubleBackHandler( LocalContext.current)
}