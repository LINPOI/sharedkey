package com.example.sharedKey.page5_account.page5_0_setAccountMain

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sharedKey.main.things.DoubleBackHandler
import com.example.sharedKey.main.things.ImageName
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.SetImage
import com.example.sharedKey.main.things.logaddress
import com.example.sharedKey.main.things.readDataClass
import com.example.sharedKey.page5_account.page5_0_setAccountMain.things.SetText
import com.example.sharedKey.page5_account.page5_0_setAccountMain.things.Setbutton
import com.example.sharedKey.page5_account.page5_0_setAccountMain.things.SignoutButton
import com.example.test1.R

@SuppressLint("SuspiciousIndentation")
@Composable
fun SetAccount(
    navController: NavController= rememberNavController(),//分頁導入
    modifier: Modifier=Modifier,
    member: Member=Member()
){
    val context=LocalContext.current
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_0_setAccountMain/SetAccount5.kt:34:16","SetAccount")
    var member= readDataClass(context,"Member") ?: Member()


    LazyColumn (
        modifier=modifier
            ,
                //讓資料欄可垂直捲動
                //.statusBarsPadding()
                // rememberScrollState()記住捲動狀態
                //.verticalScroll(rememberScrollState())
        verticalArrangement = Arrangement.Center,//水平
        horizontalAlignment = Alignment.CenterHorizontally

        ) {
        items(1) {
            SetImage(member,R.drawable.avatar,ImageName().avatar, circleShape = true, size =200)
            SetText(member)

            Spacer(modifier = Modifier.size(10.dp))

            Setbutton(navController,member,
                modifier= Modifier
                    .fillMaxWidth()
                    .size(70.dp)
            )
            if(member.account!="") SignoutButton(modifier,navController)
        }
    }
    DoubleBackHandler( LocalContext.current)
}



