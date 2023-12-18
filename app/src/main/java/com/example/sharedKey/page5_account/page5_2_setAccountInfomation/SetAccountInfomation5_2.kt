package com.example.sharedKey.page5_account.page5_2_setAccountInfomation

//////////////////
//////////////////
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.logaddress
import com.example.sharedKey.main.things.readDataClass
import com.example.sharedKey.main.things.textFields
import com.example.sharedKey.page5_account.page5_2_setAccountInfomation.things.EnrollButton
import com.example.sharedKey.page5_account.page5_2_setAccountInfomation.things.ForgotPasswordButton
import com.example.sharedKey.page5_account.page5_2_setAccountInfomation.things.LogOutButton
import com.example.sharedKey.page5_account.page5_2_setAccountInfomation.things.ModifyPasswordButton
import com.example.sharedKey.page5_account.page5_2_setAccountInfomation.things.SingInButton
import com.example.test1.R

@Composable
fun SetAccountInfomation(
    navController: NavHostController,
    modifier: Modifier,
    member: Member,
    handleBackPress: Unit
) {
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_2_setAccountInfomation/SetAccountInfomation5_2.kt:42:16")
    val member = readDataClass(LocalContext.current, "Member") ?: Member()//本來是可能為空(null)的value?，加上?:別的值讓值變回正常的value
    // Log.i("qqq",member.account)
    Surface(
        modifier = modifier,
    ) {
        Column(modifier = Modifier.padding(top = 35.dp)) {
            if (member.account != "") {
                IsSingIn(member, navController)
            } else {
                SingDoor(navController)
            }

        }
    }
}

@Composable
fun SingDoor(navController: NavHostController, modifier: Modifier = Modifier) {
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_2_setAccountInfomation/SetAccountInfomation5_2.kt:62:16")

    //列，設置寬度等同版面大小，水平排版置中，重質排版置中
    Column(
        modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        val member =
            Member(
                account = textFields(R.string.Account_text,passwordTextField = false, modifier = modifier),
                password = textFields(R.string.Password_text, modifier = modifier)
            )//使用者帳號密碼和文字框設定


        Box(modifier
            .offset(x= (-100).dp)//偏移值
        )
        {
            ForgotPasswordButton(navController)
        }
        Spacer(modifier = Modifier.padding(2.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
                EnrollButton(member = member)//註冊按鈕
                Spacer(modifier = Modifier.padding(1.dp))
                SingInButton(member = member, navController)//登入按鈕
            }




    }


}

@Composable
fun IsSingIn(member: Member, navController: NavHostController, modifier: Modifier = Modifier) {
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_2_setAccountInfomation/SetAccountInfomation5_2.kt:106:16")

    val password = "⬤⬤⬤⬤⬤⬤⬤⬤⬤"
    Column(
        modifier
            .padding(20.dp, top = 40.dp, end = 20.dp)
            .border(3.dp, Color.Black)
            .background(Color(197, 244, 253, 150))
            .padding(9.dp),
    ) {

        Text(
            text = stringResource(id = R.string.Account_text) + ":",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = member.account,
            modifier = Modifier.padding(start = 10.dp, bottom = 5.dp, top = 5.dp)
        )

        Text(
            text = stringResource(id = R.string.Password_text) + ":",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = password,
            modifier = Modifier.padding(start = 10.dp, bottom = 5.dp, top = 5.dp)
        )

        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ModifyPasswordButton(member,navController)//忘記密碼
            LogOutButton(member,navController)//註銷
        }
    }
}

