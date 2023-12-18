package com.example.sharedKey.page5_account.page5_0_setAccountMain.things

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.sharedKey.main.things.Member
import com.example.test1.R

@Composable
fun SetText(member: Member){
    /*
    顯示在帳號圖示下方的文字
    如果有暱稱顯示暱稱
    如果沒暱稱顯示帳號
    如果沒帳號顯示未登入
     */
    val user=if (member.account==""){
        stringResource(R.string.not_logged_in)
    }else if(member.nickname!=""){
        member.nickname
    } else{
        member.account
    }
    Text(
        text = stringResource(id = R.string.Traveler) +user,
        color = MaterialTheme.colorScheme.primary
    )

}