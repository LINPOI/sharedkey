package com.example.sharedKey.main.things

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


private var getinput=""
fun getinput():String{
    return getinput
}
/*
包含LAMBDA的操作
需要在使用時完成當按下完成時的操作
 */
/*
包含回傳值
已方便先將INPUT丟出外面
像是按鈕配上鍵盤完成同時可以判斷操作使用
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun textFields(stringResource: Int,
               msg:String="",
               keyboardType: KeyboardType= KeyboardType.Ascii,//設定鍵盤格式
               imeAction: ImeAction= ImeAction.Done,//設定動作按鈕
               passwordTextField:Boolean=true,
               modifier:Modifier=Modifier.fillMaxWidth(),
               setDone:(String)->Unit={null},

               ):String{
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/main/things/TextFields.kt:60:16 ")
    var input by remember {
        mutableStateOf("")
    }// 宣告一個文本變數，使用 Compose 的狀態管理功能*
    val keyboardController=LocalSoftwareKeyboardController.current
    TextField(
        modifier=modifier,
        value = input, //顯示文字
        onValueChange = { input = it },// 監聽
        label = {
            Text(text = stringResource(id=stringResource)+msg)
                },
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            setDone(input)
        }),
        keyboardOptions = KeyboardOptions(
            imeAction =  imeAction,
            //動作按鈕結束
            keyboardType =keyboardType,
            //使用鍵盤選擇

        ),
        visualTransformation=if(passwordTextField){ PasswordVisualTransformation() }else{ VisualTransformation.None }//如果不是密碼(....)就是帳號可顯示

    )
    return input
}

/*
正常的文字框，包含回傳值，直接在字串後使用此函數即可
 */
