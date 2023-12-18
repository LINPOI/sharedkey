package com.example.sharedKey.page5_account.page5_2_setAccountInfomation


//import com.example.sharedKey.main.things.updatePassword
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.sharedKey.main.Screen
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.MemberCheckCallback
import com.example.sharedKey.main.things.SetUrl
import com.example.sharedKey.main.things.Title
import com.example.sharedKey.main.things.logaddress
import com.example.sharedKey.main.things.patch
import com.example.sharedKey.main.things.post
import com.example.sharedKey.main.things.saveDataClass
import com.example.sharedKey.main.things.showToast
import com.example.sharedKey.main.things.textFields
import com.example.sharedKey.page5_account.page5_2_setAccountInfomation.things.changeGmailAddress
import com.example.sharedKey.page5_account.page5_2_setAccountInfomation.things.isPassword
import com.example.test1.R

var gonew=true
@Composable
fun ChangePassword5_2_1(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    member: Member,
    handleBackPress: Unit
) {//修改密碼時的入口
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_2_setAccountInfomation/SetNewPassword5_2_1.kt:40:16")
    val hint = LocalContext.current
    Column (
        modifier=modifier
    ){
        Log.i("0123","SetNewPassword5_2_1  ${member.password}")

        Title(text = R.string.please_enter_old_password)
        textFields(stringResource = R.string.Password_text){
            Log.i("0123","帳號:${member.account}\t舊密碼:${member.password}，輸入為${it}")
            if(it==member.password){//如果舊密碼打對
                post(mapOf(SetUrl(member).account,SetUrl(member).password), url =SetUrl().postAccountPhoneToSetNewPassword(false), callback = object:MemberCheckCallback {
                    override fun onCheckResult(result: Any) {
                        when(result){
                            1->{
                                Log.i("0123", "Oldpassword:成功收到")
                                gonew=true
                            }
                            else ->showToast(hint,R.string.wrong_password)
                        }
                    }

                })
                navController.navigate(Screen.NewPassword.name)

            }else{
                showToast(hint,R.string.wrong_password)
            }

        }


    }
}
@Composable
fun ForgetPassword5_2_2(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    handleBackPress: Unit
) {//忘記密碼
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_2_setAccountInfomation/SetNewPassword5_2_1.kt:76:16")


    val member = Member()
    val hint = LocalContext.current
    Column(
        modifier=modifier


    ) {
        Title(R.string.please_fill_in_the_following_information)
        member.account = textFields(R.string.Gmail, imeAction = ImeAction.Next, passwordTextField = false)
        member.account = changeGmailAddress(member.account) ?: ""
        textFields(stringResource = R.string.Telephone, keyboardType = KeyboardType.Number, passwordTextField = false){
            member.phone=it
            post(mapOf(SetUrl(member).account,SetUrl(member).phone),url=SetUrl().postAccountPhoneToSetNewPassword(true), callback = object:MemberCheckCallback {
                override fun onCheckResult(result: Any) {
                    if (result is Int && result==1) {
                        saveDataClass(hint,"Member",member)
                        navController.navigate(Screen.NewPassword.name)

                    }else{
                        showToast(hint,R.string.not_find)
                    }
                }
            })
            Log.i("0123","$member")

        }

    }

}

@Composable
fun Newpassword(modifier: Modifier=Modifier,navController: NavHostController,member: Member, handleBackPress: Unit) {//設置新密碼
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_2_setAccountInfomation/SetNewPassword5_2_1.kt:103:16")

    val hint = LocalContext.current
    val forgetPassword= member.password==""

    //if(!gonew) navController.navigate("page5_2")//如果非法進入就跳出去
    Log.i("0123","Newpassword  ${member.password}")
    Column (
        modifier=modifier
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Title(R.string.please_enter_a_new_password)
        val password1=textFields(stringResource = R.string.please_enter_a_new_password, imeAction = ImeAction.Next)
        val password2= textFields(stringResource = R.string.please_enter_new_password_again){
            newactionnewpassword(password1,it,member,hint, forgetPassword,navController)
        }

        Row (
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Button(
                onClick = {
                navController.navigate(Screen.ForgotPassword.name){
                    popUpTo("page5_2"){
                        inclusive = true
                    }
                }
                showToast(hint, R.string.cancel)
                })
            {
                Text(text = stringResource(R.string.cancel))

            }
            Button(onClick = {
                Log.i("0123","button click")
                newactionnewpassword(password1,password2,member,hint, forgetPassword, navController)
            }) {
                Text(text = stringResource(R.string.confirm))
            }
        }

    }
}


/*
設置更新密碼後的動作
 */
@SuppressLint("SuspiciousIndentation")
fun newactionnewpassword(password:String, password2:String, member:Member, hint:Context, forgetPassword:Boolean, navController:NavHostController){


    if(password==password2){//密碼1等於密碼2
        if(member.password==password)showToast(hint, R.string.the_new_password_cannot_be_the_same_as_the_old_password)
        Log.i("0123","Newpassword，帳密相等:  ${member.password}")
        if (password.length < 8) {
            showToast(context = hint, R.string.password_is_less_than_eight_characters)
        }else if(!isPassword(password)){
            showToast(context = hint,
                R.string.password_contains_illegal_characters)
        } else {
            if(forgetPassword){//忘記密碼
                /*
                先驗證帳號電話
                再pacth新密碼
                 */
                member.password=password2
                patch(mapOf( SetUrl(member).account,SetUrl(member).password),SetUrl().postAccountPhoneToSetNewPassword(true))

            }else{
                /*
                先驗證帳號密碼
                再pacth新密碼
                 */
                post(mapOf(SetUrl(member).account,SetUrl(member).password), url =SetUrl().postAccountPhoneToSetNewPassword(false), callback = object:MemberCheckCallback{
                    override fun onCheckResult(result: Any) {
                        when(result){
                            1-> {
                                member.password=password2
                                patch(mapOf( SetUrl(member).account,SetUrl(member).password),SetUrl().postAccountPhoneToSetNewPassword(false))

                            }
                        }
                    }

                })

            }
            //丟callback會跳掉
            showToast(hint, R.string.please_log_in_again)
            saveDataClass(hint,"Member",Member())
            navController.navigate(Screen.AccountInformation.name){
                popUpTo(Screen.AccountInformation.name){
                    inclusive = true
                }
            }
        }

    }else{
        showToast(hint, R.string.passwords_are_inconsistent)
    }

}