package com.example.sharedKey.page5_account.page5_4_setWallet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.isChinese
import com.example.sharedKey.main.things.logaddress
import com.example.test1.R

@Composable
fun SetWallet(modifier: Modifier = Modifier, member: Member, handleBackPress: Unit){
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_4_setWallet/SetWallet.kt:30:16","SetWallet")
    Row (
        modifier=modifier


            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround

    ){
        SetButton()
        SetButton(true)
    }
}
@Composable
fun SetButton(payment:Boolean=false) {
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/page5_account/page5_4_setWallet/SetWallet.kt:46:16")

    TextButton(
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .size(height = 500.dp, width = 150.dp)
                .border(1.dp, MaterialTheme.colorScheme.onBackground)
                .background(
                    if (payment) {
                        MaterialTheme.colorScheme.errorContainer
                    } else {
                        MaterialTheme.colorScheme.primaryContainer
                    }
                )  ,

            onClick = { /*TODO*/ }
        ) {
            Text(
                text =  stringResource(if(payment){ R.string.payment }else{ R.string.income }),
                fontSize =if(isChinese()) {//防止跑版
                    40.sp
                }else{
                    28.sp
                     },
                color = if(payment){MaterialTheme.colorScheme.onErrorContainer}else{MaterialTheme.colorScheme.onPrimaryContainer}
            )
        }

}