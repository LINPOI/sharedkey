package com.example.sharedKey.main

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sharedKey.main.things.showToast
import com.example.test1.R

@Composable
fun OpenRow(modifier: Modifier=Modifier,navController: NavController = rememberNavController()) {

    Row (

        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier=modifier
            .background(MaterialTheme.colorScheme.onSecondary)
            .fillMaxWidth()
            //.border(0.1.dp, MaterialTheme.colorScheme.onBackground)

    ){

        for (i in 0 .. 4){
            IconButtons(i,navController)
        }
    }
}
@Composable
fun IconButtons(which:Int,navController: NavController){
    //Log.i("0123"," file:///C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/main/OpenRow.kt")
    val context= LocalContext.current
    val imageIs= when(which){
        0 -> R.drawable.explore
        1 -> R.drawable.wish
        2 -> R.drawable.travel
        3 -> R.drawable.message
            else ->R.drawable.account
    }
    val imageName = when(which){
        0 -> R.string.explore
        1 -> R.string.wishlist
        2 -> R.string.travel
        3 -> R.string.message
        else ->R.string.account
    }
    var isImageClicked by remember { mutableStateOf(false) }
    Image(
        painter = painterResource(id = imageIs),
        contentDescription = null,
        modifier = Modifier
            .padding(bottom = 0.5.dp)
            //.border(0.5.dp,Color.Black)
            .size(width = 71.9.dp,height = 60.dp)
            .padding( top = 5.dp)
            .animateContentSize()
            .pointerInput(Unit){
                detectTapGestures(
                    onPress = {
                        isImageClicked = true
                    },
                    onLongPress ={
                        showToast(context,imageName )
                    }
                )
            }
            //保護不備ui系統遮擋
            .safeDrawingPadding(),
    )
    LaunchedEffect(isImageClicked) {
        if (isImageClicked) {
            when (which) {
                0 -> navController.navigate(Screen.Explore.name)
                1 -> navController.navigate(Screen.Wishlist.name)
                2 -> navController.navigate(Screen.Travel.name)
                3 -> navController.navigate(Screen.Message.name)
                else -> navController.navigate(Screen.Account.name)
                //{ popUpTo("page1") { inclusive = true } }確保導航正確位置已不需要
            }
            isImageClicked = false
        }
    }

}