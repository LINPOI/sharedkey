package com.example.sharedKey.main

import SetHousesInf5_3_1
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sharedKey.main.things.HandleBackPress
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.readDataClass
import com.example.sharedKey.page1_explore.SetExplore
import com.example.sharedKey.page2_wish.SetWish
import com.example.sharedKey.page3_travel.SetTrave
import com.example.sharedKey.page4_message.SetMessage
import com.example.sharedKey.page5_account.page5_0_setAccountMain.SetAccount
import com.example.sharedKey.page5_account.page5_1_setMember.SetMember5_1
import com.example.sharedKey.page5_account.page5_1_setMember.setextfieid5_1_1.SetTextField5_1_1
import com.example.sharedKey.page5_account.page5_2_setAccountInfomation.ChangePassword5_2_1
import com.example.sharedKey.page5_account.page5_2_setAccountInfomation.ForgetPassword5_2_2
import com.example.sharedKey.page5_account.page5_2_setAccountInfomation.Newpassword
import com.example.sharedKey.page5_account.page5_2_setAccountInfomation.SetAccountInfomation
import com.example.sharedKey.page5_account.page5_3_setHouses.SelectInf5_3_1_1
import com.example.sharedKey.page5_account.page5_3_setHouses.SetHouese5_3
import com.example.sharedKey.page5_account.page5_4_setWallet.SetWallet
import com.example.test1.R


/**
 * enum values that represent the screens in the app
 */
enum class Screen(@StringRes val title: Int) {//抽象類
    Explore(title = R.string.explore),
    Wishlist(title = R.string.wishlist),
    Travel(title = R.string.travel),
    Message(title = R.string.message),
    Account(title=R.string.account),
    /*
    5-*
     */
    MemberInf(title = R.string.Membership_Information),
    AccountInformation(title = R.string.Account_management),
    House(title = R.string.Become_a_renter),
    Wallet(title = R.string.Income_And_Payments),
    /*
    5-2-*
     */
    EditProfile(title = R.string.Edit_profile),
    modifyPassword(title = R.string.modify_password),
    ForgotPassword(title = R.string.Forgot_password),
    NewPassword(title = R.string.new_password),

    /*
    5-3-*/
    HousesInf(title = R.string.house_inf),
    SelectInf(title = R.string.please_choose)

}
@Composable
fun AppNavigator(modifier:Modifier =Modifier ,navController: NavHostController = rememberNavController()) {
    val context= LocalContext.current
    val backStackEntry by navController.currentBackStackEntryAsState()
    val member= readDataClass(context,"Member")?: Member()
    // Get the name of the current screen
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Explore.name//當前路徑，route回傳名稱
    )

    Scaffold(
        bottomBar ={
            OpenRow(modifier = modifier,navController=navController)
        }  ,
    ) {innerPadding ->
        NavHost(navController, startDestination = currentScreen.name,modifier=modifier.padding(innerPadding).background(MaterialTheme.colorScheme.background)) {
            Log.i("0123",currentScreen.name)
            /*
                1.搜索
             */
            composable(Screen.Explore.name){
                SetExplore(
                    navController,
                    modifier=Modifier
                        .fillMaxSize(),
                    )
            }
            /*
            2.願望清單
             */
            composable(Screen.Wishlist.name){
                SetWish(
                    navController,
                    modifier=Modifier
                        .fillMaxSize(),
                )
            }
            /*
            3.行程
             */
            composable(Screen.Travel.name){
                SetTrave(
                    navController,
                    modifier=Modifier
                    .fillMaxSize(),
                )
            }
            /*
            4.訊息
             */
            composable(Screen.Message.name){
                SetMessage(
                    navController,
                    modifier=Modifier
                        .fillMaxSize(),
                )
            }
            /*
            5.帳號
             */
            composable(Screen.Account.name){
                SetAccount(
                    navController,
                    modifier=Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(top = 20.dp),
                    member
                )
            }
            /*
            5-1會員資料
             */

            composable(Screen.MemberInf.name) {
                SetMember5_1(
                    navController,
                    Modifier,
                    HandleBackPress {//返回時觸發，正確返回用
                        navController.navigate(Screen.Account.name)
                    }
                )
            }
            /*
            5-1-1填寫會員資料
             */
            composable(Screen.EditProfile.name) {
                SetTextField5_1_1(
                    navController,
                    modifier=Modifier
                        .fillMaxSize(),
                    member,
                    HandleBackPress {
                        navController.navigate(Screen.MemberInf.name)
                    }
                )
            }
            /*
            5-2帳號管理
             */
            composable(Screen.AccountInformation.name) {
                SetAccountInfomation(
                    navController,modifier=Modifier
                        .fillMaxSize(),
                    member,
                    HandleBackPress {
                        navController.navigate(Screen.Account.name)
                    }
                )
            }
            /*
           5-2-1修改密碼
            */
            composable(Screen.modifyPassword.name){
                ChangePassword5_2_1(navController,
                    modifier=Modifier
                        .fillMaxWidth(),
                    member,
                    HandleBackPress {
                        navController.navigate(Screen.AccountInformation.name)
                    }
                )
            }
            /*
          5-2-2忘記密碼
           */
            composable(Screen.ForgotPassword.name){
                ForgetPassword5_2_2(navController,
                    modifier=Modifier
                        .fillMaxSize(),
                    HandleBackPress {
                        navController.navigate(Screen.AccountInformation.name)
                    }
                )
            }
            /*
          5-2-3更新密碼
           */
            composable(Screen.NewPassword.name){
                Newpassword(
                    navController=navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    member = member,
                    handleBackPress = HandleBackPress {
                        navController.navigate(Screen.AccountInformation.name)
                    }
                )
            }
            /*
            5-3成為租屋人
             */
            composable(Screen.House.name){
                SetHouese5_3(
                    navController,
                    modifier=Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    member,
                    HandleBackPress {
                        navController.navigate(Screen.Account.name)
                    }
                )
            }
            /*
            5-3-1設定房屋資料
             */
            composable(Screen.HousesInf.name){
                SetHousesInf5_3_1(
                    navController,
                    modifier=Modifier,
                    member,
                    HandleBackPress {
                        navController.navigate(Screen.House.name)
                    }
                ) }
            /*
            5-3-1-1設定房屋資料
             */
            composable(Screen.SelectInf.name){
                SelectInf5_3_1_1(
                    navController,
                    modifier=Modifier,
                    member
                ) }
            /*
            5-4收入/付款
             */
            composable(Screen.Wallet.name){
                SetWallet(
                    modifier=Modifier
                        .fillMaxSize(),
                    member,
                    HandleBackPress {
                        navController.navigate(Screen.Account.name)
                    }
                )
            }//收入/付款
        }

    }

}



//
//
//
//