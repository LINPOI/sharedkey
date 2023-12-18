package com.example.sharedKey.page1_explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.sharedKey.main.things.DoubleBackHandler
import com.example.test1.R


@Composable
fun SetExplore(
    navHostController: NavHostController, modifier: Modifier//分頁導入

){
    LazyColumn(
        modifier=modifier,
        verticalArrangement = Arrangement.Center,//水平(左右)
        horizontalAlignment = Alignment.CenterHorizontally//垂直(上下)
    ){
        //Logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page1_explore/SetExplore.kt:29:20","SetExplore")

        items(1) {
        Text(text = stringResource(R.string.explore))

        }
    }
    DoubleBackHandler( LocalContext.current)

}