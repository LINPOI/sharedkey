package com.example.sharedKey.page5_account.page5_3_setHouses

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sharedKey.main.Screen
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.MemberCheckCallback
import com.example.sharedKey.main.things.SetUrl
import com.example.sharedKey.main.things.arrayString
import com.example.sharedKey.main.things.getForOne
import com.example.sharedKey.main.things.logaddress
import com.example.sharedKey.main.things.post

@Composable
fun SetHouese5_3(
    navHostController: NavHostController,
    modifier: Modifier,
    member: Member,
    handleBackPress: Unit
){
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_3_setHouses/SetHouses.kt:30:16 ")
    Column (
        modifier= modifier

    ){
        AddHouse(navHostController,member)
    }

}
@Composable
fun AddHouse(navHostController: NavHostController, member: Member){
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_3_setHouses/SetHouses.kt:42:16 ")
    var items by remember {
        mutableIntStateOf(1)
    }
    LazyColumn{
        items(items) {
            IconButton(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .background(Color.White)
                    .border(1.5.dp, MaterialTheme.colorScheme.onBackground)
                    .size(200.dp),
                onClick = {
                    Log.i("0123","add$items")
                    post(mapOf(SetUrl(member).account, SetUrl(member).house_name, SetUrl(member).address), url= SetUrl().postHouseDataUrl())
                    getForOne(member,SetUrl().getHouseDataUrl(),object:MemberCheckCallback{
                        override fun onCheckResult(result: Any) {
                            if(arrayString(result)){

                            }
                        }

                    })
                    navHostController.navigate(Screen.HousesInf.name)
                    //items++
                }
            ) {
                Icon(
                    Icons.Default.Add, contentDescription = "Add"
                )
            }
        }

    }
}
