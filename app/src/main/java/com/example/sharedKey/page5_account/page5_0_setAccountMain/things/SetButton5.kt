package com.example.sharedKey.page5_account.page5_0_setAccountMain.things
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sharedKey.main.Screen
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.ShowAlertDialog
import com.example.sharedKey.main.things.saveDataClass

import com.example.sharedKey.main.things.showToast
import com.example.test1.R

@Composable
fun Setbutton(navController: NavController, member: Member,modifier: Modifier) {

    val currentBackStackEntry: NavBackStackEntry? =
        navController.currentBackStackEntryAsState().value
    for (i in 1..4) {
        val hint = LocalContext.current//設置toast
        val btntext = when (i) {
            1 -> R.string.Membership_Information
            2 -> R.string.Account_management
            3 -> R.string.Become_a_renter
            else -> R.string.Income_And_Payments
        }
        OutlinedButton(
            modifier = modifier
                ,
            shape = MaterialTheme.shapes.extraLarge,
            onClick = {
                if (currentBackStackEntry?.destination?.route == Screen.Account.name) {//確保按鈕是在page5觸發，避免重複觸發
                    if (i == 1 && member.account != "") {
                        navController.navigate(Screen.MemberInf.name)//會員資料
                    } else if (i == 1) {
                        showToast(
                            hint,
                            R.string.please_log_in_to_your_account_first
                        )
                        navController.navigate(Screen.AccountInformation.name)//帳號管理
                    }
                    if (i == 2) {
                        navController.navigate(Screen.AccountInformation.name)//帳號管理
                    }
                    if (i == 3&& member.account != "") {
                        navController.navigate(Screen.House.name)//租屋人
                    } else if (i == 3) {
                        showToast(
                            hint,
                            R.string.please_log_in_to_your_account_first
                        )
                        navController.navigate(Screen.AccountInformation.name)//帳號管理
                    }
                    if (i == 4&& member.account != "") {
                        navController.navigate(Screen.Wallet.name)//收入/付款
                    } else if (i == 4) {
                        showToast(
                            hint,
                            R.string.please_log_in_to_your_account_first
                        )
                        navController.navigate(Screen.AccountInformation.name)//帳號管理
                    }
                }
            }

        ) {
            Text(
                text = stringResource(btntext),
                fontSize = 25.sp
            )
        }
        Spacer(modifier = Modifier.size(5.dp))
    }

}

@Composable
fun SignoutButton(modifier: Modifier,navController:NavController) {
    var singoutisclick by remember {
        mutableStateOf(false)
    }
    val hint =LocalContext.current
    Row (
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End

    ){
        TextButton(
            modifier = Modifier.alpha(0.4f),

            //////////////////////////////////////////////////////////////press
            onClick = {
                singoutisclick=true
            }
            ///////////////////////////////////////////////////////////
        ) {
            Text(
                text = stringResource(R.string.sign_out),
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.End
            )
        }
        if(singoutisclick){
            ShowAlertDialog(stringResource(id = R.string.sign_out),
                stringResource(R.string.please_confirm_whether_to_exit), onConfirm = {
                saveDataClass(hint, "Member", Member())
                navController.navigate(Screen.Account.name)
                singoutisclick=false
            }, onDismiss = {singoutisclick=false})
        }

    }

}
