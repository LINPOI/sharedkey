package com.example.sharedKey.page5_account.page5_1_setMember

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sharedKey.main.Screen
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.logaddress
import com.example.sharedKey.main.things.readDataClass
import com.example.sharedKey.main.things.saveDataClass
import com.example.sharedKey.main.things.showToast
import com.example.test1.R

@Composable
fun SetMember5_1(navController: NavController, modifier: Modifier = Modifier, handleBackPress: Unit) {
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_1_setMember/SetMember5_1.kt:38:16")
    val member= readDataClass(LocalContext.current,"Member") ?: Member()
    //Log.i("0123","d"+member.account)

    LazyColumn (
        modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ){
        items(1) {
            MainTitle()
            for (i in 1..5) {
                Spacer(modifier = modifier.padding(7.dp))
                Buttons(i,modifier, member,navController)
            }
        }
    }
}
@Composable
fun Buttons(num:Int, modifier: Modifier, member: Member, navController: NavController){

    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_1_setMember/SetMember5_1.kt:58:16")

    val hint = LocalContext.current
    OutlinedButton(
        shape = MaterialTheme.shapes.large,
        onClick = {
            Log.i("0123","button:$num")
            saveDataClass(hint,"save",num)
            if(num!=3){
                navController.navigate(Screen.EditProfile.name) {
                    popUpTo(Screen.EditProfile.name) {
                        inclusive = true
                    }
                }
            }else{
                showToast(hint,
                    R.string.the_email_has_been_bound_please_do_not_modify_it
                )
            }
        }
    ) {
        ////////////////////////////////////////////////
        Column (
            modifier
                .fillMaxSize()
        ) {
            Title(num)
            ////////////////////////////////////////////
            Spacer(modifier = modifier.padding(4.dp))
            Row(
                modifier
                    .fillMaxWidth()
                    .size(30.dp)

                ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = modifier.padding(1.dp))
                UserInf(num,member)
                Icongo(num,member)
            }
            /////////////////////////////////////////////
        }
        /////////////////////////////////////////////////
    }
}
@Composable
fun MainTitle(){
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_1_setMember/SetMember5_1.kt:106:16")

    Text(
        text = stringResource(R.string.Edit_profile),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.inversePrimary)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground
    )
}
@Composable
fun Title(num:Int){
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_1_setMember/SetMember5_1.kt:120:16")

    val textnum=when (num) {
        1 -> R.string.Nickname
        2 -> R.string.Name
        3 -> R.string.Gmail
        4 -> R.string.Telephone
        else -> R.string.Id
    }

    Text(
        text = stringResource(textnum),
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodyLarge
    )
}
@Composable
fun UserInf(num: Int, member: Member){
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_1_setMember/SetMember5_1.kt:138:16")

    val inf=when (num) {
        1 -> member.nickname
        2 -> showPresonalInformation(member.name)
        3 -> member.account
        4 -> showPresonalInformation (member.phone)
        else -> showPresonalInformation (member.id)
    }

    Text(
        text = if(inf!=""){ inf }else{"未填入"},
        textDecoration = TextDecoration.Underline,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}
@Composable
fun Icongo(num: Int,member: Member){
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_1_setMember/SetMember5_1.kt:156:16","正常")

    val inf=when (num) {
        1 -> member.nickname
        2 -> member.name
        3 -> member.account
        4 -> member.phone
        else -> member.id
    }
    Icon(
        if(inf==member.account){
            Icons.Default.MailOutline
        }else{
            Icons.Default.KeyboardArrowRight
        }, contentDescription = null,
    )
}

fun showPresonalInformation(string: String):String{
    val long=string.length
    var output=""
    if(string=="")return ""
    if(long==2){
        output=string[0]+"*"
    }else if(long<=5){
        output+=string[0]
        for(i in 1 until  long-1 ){
            output+="*"
        }
        output+=string[long-1]
    }else{
        output+=string.substring(0,2)
        for(i in 2 until long-2){
            output+="*"
        }
        output+=string.substring(long-2,long)
    }
    return output
}