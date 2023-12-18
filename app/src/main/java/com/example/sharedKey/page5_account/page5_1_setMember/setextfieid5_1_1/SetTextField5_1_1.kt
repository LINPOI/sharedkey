package com.example.sharedKey.page5_account.page5_1_setMember.setextfieid5_1_1

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.sharedKey.main.Screen
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.MemberCheckCallback
import com.example.sharedKey.main.things.SetUrl
import com.example.sharedKey.main.things.Title
import com.example.sharedKey.main.things.patch
import com.example.sharedKey.main.things.readDataClass
import com.example.sharedKey.main.things.saveDataClass
import com.example.sharedKey.main.things.showToast
import com.example.sharedKey.main.things.textFields
import com.example.test1.R

@Composable
fun SetTextField5_1_1(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    member: Member,
    handleBackPress: Unit
) {
    Surface (
        modifier=modifier
    ){
        val hint= LocalContext.current
        var isok: Boolean

        val num= readDataClass(hint,"save",0)
        val member= readDataClass(hint,"Member") ?: Member()
        Row (modifier
            .fillMaxWidth(),
            ){
            val text=when(num){
                1 -> R.string.Nickname
                2 -> R.string.Name
                3 -> R.string.Gmail
                4 -> R.string.Telephone
                else -> R.string.Id
            }

            Column (modifier = Modifier){
                Title(othertext = "編輯", text = text,)
                val input=textFields(text, msg = when(num){
                    1->"(請輸入兩個字以上)"
                        2->"(請輸入兩個字以上)"
                        else ->""
                },
                    keyboardType = when(num) {
                        1-> KeyboardType.Text
                        2-> KeyboardType.Text
                        4 -> KeyboardType.Number
                        else -> KeyboardType.Ascii
                    }
                    ,
                    passwordTextField = false
                )
                isok = !(((num==2||num==1)&& input.length<2)||((num==4||num==5)&&input.length<10))
                val done=buttons(modifier = modifier, navController =navController,isok,num,input )


                when(num){
                    1 -> member.nickname=input
                    2 -> member.name=input
                    //3 -> member.account=input
                    4 -> member.phone=input
                    else -> member.id=input.uppercase()
                }
                if(isok&&done){

                    patch(mapOf(SetUrl(member).nickname,SetUrl(member).name,SetUrl(member).gmail,SetUrl(member).phone,SetUrl(member).id),SetUrl().patchUserDataUrl(),object : MemberCheckCallback {
                        override fun onCheckResult(result: Any) {
                            when(result){
                                1 -> {
                                    Log.i("0123","帳號{${member.account}}資料中的{${SetUrl().setMemberNum(num+1)}}已更新為:{$input}")
                                    saveDataClass(hint,"Member",member)
                                }
                                2->{
                                        showToast(hint, R.string.registered)

                                }
                                else -> Log.i("0123","更新失敗")
                            }
                        }

                    })
                }

            }

        }


    }

}

@Composable
fun buttons(
    modifier: Modifier,
    navController: NavHostController,
    isok: Boolean = false,
    num: Int=0,
    input: String=""
): Boolean {
    val hint= LocalContext.current
    var done by remember {
        mutableStateOf(false)
    }
    Row(
        modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier,
            onClick = {
                navController.navigate(Screen.MemberInf.name) {
                    popUpTo(Screen.MemberInf.name) {
                        inclusive = true
                    }
                }
            }
        ) {
            Icon(Icons.Default.Close, contentDescription = null)
        }
        IconButton(
            modifier = Modifier,
            onClick = {
                if (isok){
                    if(num==5&&!isID(input)){
                        showToast(hint,
                           R.string.there_seems_to_be_an_error_in_the_format
                        )
                    }else if(num==4&& !isPhoneNumber(input)){
                        showToast(hint,R.string.there_seems_to_be_an_error_in_the_format)
                    }else{
                        navController.navigate(Screen.MemberInf.name)
                        done=true
                    }
                }
            }
        ) {
            Icon(
                Icons.Default.Done, contentDescription = null,
                modifier.alpha(
                    if (isok) {
                        1f
                    } else {
                        0.2f
                    }
                )
            )
        }
    }
    return done
}
fun isID(id: String): Boolean {
    val regex = Regex("^[A-Za-z]?[0-9]{9}$")
    return regex.matches(id)
}
fun isPhoneNumber(phoneNumber: String): Boolean {
    val regex = Regex("^0[0-9]{9}$")
    return regex.matches(phoneNumber)
}
