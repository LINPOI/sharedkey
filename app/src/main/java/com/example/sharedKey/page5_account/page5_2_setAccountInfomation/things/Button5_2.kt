package com.example.sharedKey.page5_account.page5_2_setAccountInfomation.things

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sharedKey.main.Screen
import com.example.sharedKey.main.things.Logaddress
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.MemberCheckCallback
import com.example.sharedKey.main.things.SetUrl
import com.example.sharedKey.main.things.ShowAlertDialog
import com.example.sharedKey.main.things.arrayString
import com.example.sharedKey.main.things.delete
import com.example.sharedKey.main.things.getForAll
import com.example.sharedKey.main.things.getForOne
import com.example.sharedKey.main.things.post
import com.example.sharedKey.main.things.saveDataClass
import com.example.sharedKey.main.things.showToast
import com.example.test1.R


//註冊按鈕設置
@Composable
fun EnrollButton( member: Member) {
    //Logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_2_setAccountInfomation/things/Button5_2.kt:40:5")
    val hint = LocalContext.current//設置toast
    OutlinedButton(
        modifier = Modifier.size(150.dp,50.dp),
        shape = MaterialTheme.shapes.large,
        onClick = {
            Log.i("0123", "EnrollButton:點擊註冊")
            if(member.account!="" && member.password!="")
                {
                    member.account = changeGmailAddress(member.account) ?: ""
                    member.account = extraSpaces(member.account)
                    member.password = extraSpaces(member.password)
                }
            if (!isGmailAddress(member.account)) {
                showToast(hint,R.string.not_in_gmail_format)
            }  else if (member.password.length < 8) {
                showToast(context = hint, R.string.password_is_less_than_eight_characters)
            }else if(!isPassword(member.password)){
                showToast(context = hint,
                    R.string.password_contains_illegal_characters)
            } else {
                Log.i("0123", "EnrollButton:帳號:${member.account}\n密碼:${member.password}")
                //readHttpRequest(member,context = hint,false)
                getForAll(
                    member,
                    SetUrl().getAccountDataUrl(),
                    object : MemberCheckCallback {
                        override fun onCheckResult(result: Any) {
                            // 處理回傳值
                            //
                            Log.i("0123", "EnrollButton:回傳值: $result")
                            if (result == 0) {//帳號不存在
                                post(
                                    mapOf(SetUrl(member).account,SetUrl(member).password) ,
                                    url=SetUrl().postAccountDataUrl(),
                                    callback = object : MemberCheckCallback {
                                        override fun onCheckResult(result: Any) {
                                            if (result == 1) {
                                                showToast(context = hint, R.string.registration_success,insurance = true)
                                                //本地儲存
                                                //
                                                saveDataClass(hint, "Member", member)
                                                //新增用戶資料表
                                                //postUserData(member, SetApi().postUserDataApi())
                                            } else {
                                                Log.i("0123", "註冊失敗!!!")
                                                showToast(context = hint, R.string.registration_success,insurance = true)
                                            }
                                        }
                                    })

                            } else {
                                showToast(context = hint,
                                    R.string.account_already_exists)
                            }
                        }
                    })

            }
        },

        ) {
        Text(text = stringResource(R.string.Enroll), fontSize = 20.sp)
    }
}

//登入按鈕設置
@Composable
fun SingInButton(member: Member, navController: NavHostController) {
    //Logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_2_setAccountInfomation/things/Button5_2.kt:109:5")

    val hint = LocalContext.current//設置toast
    OutlinedButton(
        modifier = Modifier.size(150.dp,50.dp),
        shape = MaterialTheme.shapes.large,
        onClick = {

            if(member.account!="" && member.password!=""){
                member.account=changeGmailAddress(member.account)?:""
                member.account=extraSpaces(member.account)
                member.password=extraSpaces(member.password)
            }

            Log.i(
                "0123", "SingInButton:點擊登入帳號:${member.account}\n" +
                        "密碼:${member.password}"
            )
            if (!isGmailAddress(member.account)) {
                showToast(hint, R.string.not_in_gmail_format)
            } else if (member.password.length < 8) {//密碼不可小於八個字
                showToast(context = hint, R.string.password_is_less_than_eight_characters)
            } else if(!isPassword(member.password)){
                showToast(context = hint,
                    R.string.password_contains_illegal_characters)
            }else {
                getForAll(
                    member,
                    SetUrl().getAccountDataUrl(),
                    object : MemberCheckCallback {
                        override fun onCheckResult(result: Any) {
                            // 在这里处理isRead的值，而不是直接返回它
                            Log.i("0123", "SingInButton:回傳值: $result")
                            when (result) {
                                1 -> {
                                    showToast(context = hint,
                                        R.string.sign_in_suceesfully)
                                    //預留空間準備跳下一階段
                                    getForOne(member, SetUrl().getUserDataUrl(member.account),object : MemberCheckCallback {
                                        override fun onCheckResult(result: Any) {
                                            if (arrayString(result)) {
                                                // result 是 Array<String> 类型
                                                val stringArray = result as Array<String>
                                                member.nickname=stringArray[0]
                                                member.name=stringArray[1]
                                                member.gmail=stringArray[2]
                                                member.phone=stringArray[3]
                                                member.id =stringArray[4]
                                                saveDataClass(hint, "Member", member)
                                                Log.i("0123", "singin:回傳值: $result \n member $member ")
                                            }
                                        }

                                    })
                                    navController.navigate(Screen.Account.name)
                                }

                                2 -> {
                                    showToast(context = hint,
                                        R.string.wrong_password)
                                }

                                else -> {
                                    showToast(context = hint,
                                       R.string.account_does_not_exist)
                                }
                            }

                        }

                    })
            }
        },

        ) {
        Text(text = stringResource(R.string.Log_in_Btntext), fontSize = 20.sp)
    }
}


//忘記密碼按鈕
@Composable
fun ForgotPasswordButton(navController: NavHostController) {//忘記密碼
   // Logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_2_setAccountInfomation/things/Button5_2.kt:191:5")

    TextButton(
            modifier = Modifier
                .height(50.dp) // 設置按鈕高度為 50 dp
                .wrapContentWidth(), // 寬度隨內容自適應
            onClick = { navController.navigate(Screen.ForgotPassword.name) }) {
            Text(
                text = stringResource(R.string.Forgot_password),
                textDecoration = TextDecoration.Underline,
                fontSize = 13.sp
            )
        }
}


//修改密碼按鈕
@Composable
fun ModifyPasswordButton(member: Member, navController: NavHostController) {//修改密碼
    Logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_2_setAccountInfomation/things/Button5_2.kt:210:5")

    TextButton(
        modifier = Modifier
            .height(50.dp) // 設置按鈕高度為 50 dp
            .wrapContentWidth(), // 寬度隨內容自適應
        onClick = {
            navController.navigate(Screen.modifyPassword.name)
        }) {
        Text(
            text = stringResource(R.string.modify_password),
            textDecoration = TextDecoration.Underline,
            fontSize = 13.sp
        )
    }
}


//註銷按鈕
@Composable
fun LogOutButton(member: Member,navController: NavHostController) {
    Logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_2_setAccountInfomation/things/Button5_2.kt:231:5")

    val hint = LocalContext.current
    var singoutisclick by remember {
        mutableStateOf(false)
    }
    TextButton(
        onClick = {
            singoutisclick = true
        }) {
        Text(
            text = stringResource(R.string.Logout),
            color = Color(0XFFDC143C),
            textDecoration = TextDecoration.Underline
        )
    }
    if (singoutisclick) {
        ShowAlertDialog(stringResource(id = R.string.Logout),
            stringResource(R.string.please_confirm_whether_to_log_out), onConfirm = {
                saveDataClass(hint, "Member", Member())
                delete(mapOf(SetUrl(member).account,SetUrl(member).password), SetUrl().deleteAccountDataUrl())
                navController.navigate(Screen.Account.name)
                singoutisclick = false
            }, onDismiss = { singoutisclick = false })
    }
}

//gmail檢查
fun isGmailAddress(email: String): Boolean {
    val gmailPattern = Regex("^(?!.*\\.\\.)[a-zA-Z0-9.]+@gmail\\.com\$")
    return gmailPattern.matches(email)
}
//password檢查
fun isPassword(password: String): Boolean {
    val gmailPattern = Regex("^[a-zA-Z0-9.@$]*$")
    return gmailPattern.matches(password)
}
//轉換為gmail
fun changeGmailAddress(gmail:String): String? {
    val gmailPattern = Regex("^(?!.*\\.\\.)[a-zA-Z0-9.]+@gmail\\.com\$")
    return when {
        gmail=="invalid"->null
        gmailPattern.matches(gmail) -> gmail // 已經是有效的 Gmail 地址
        gmail.isNotEmpty() -> "$gmail@gmail.com" // 在非空的情況下附加 @gmail.com
        else -> "invalid" // 其他情況，可能是空字串或無效格式
    }
}
//多一個空格檢查
fun extraSpaces(input:String):String{
    var output=""
    if(input[input.length-1]==' '){
        for(i in 0..input.length-2){
            output+=input[i]
        }
    }else{
        output=input
    }
    return output
}