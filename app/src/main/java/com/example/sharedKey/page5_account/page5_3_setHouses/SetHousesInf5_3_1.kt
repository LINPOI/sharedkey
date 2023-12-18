
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sharedKey.main.Screen
import com.example.sharedKey.main.things.HouseClass
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.SetImage
import com.example.sharedKey.main.things.boxHaveClose
import com.example.sharedKey.main.things.imageName
import com.example.sharedKey.main.things.keyName
import com.example.sharedKey.main.things.numberToggleButtons
import com.example.sharedKey.main.things.readDataClass
import com.example.sharedKey.main.things.saveDataClass
import com.example.sharedKey.main.things.textFields
import com.example.sharedKey.page5_account.page5_3_setHouses.HouseTypeStringID
import com.example.test1.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun SetHousesInf5_3_1(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    member: Member,
    handleBackPress: Unit,
) {
    Log.i("0123", "進入房屋編輯")
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,//水平(左右)
        horizontalAlignment = Alignment.CenterHorizontally//垂直(上下)
    ) {

        items(1) {
            SetImage(
                member,
                initialPicture = null,
                fileName = imageName.housePicture(4, "1"),
                circleShape = false,
                width = 400,
                height = 200
            )
            val houseClass = HouseClass(
                name = textFields(
                    stringResource = R.string.house_name,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    passwordTextField = false
                ),
                address = textFields(
                    stringResource = R.string.address,
                    keyboardType = KeyboardType.Text,
                    passwordTextField = false
                )
            )
            Spacer(modifier = Modifier.padding(20.dp))
            houseClass.type.guests = toggleNumberRow(R.string.guests, buttonNumber = 20)
            houseClass.type.bedroom = toggleNumberRow(stringId = R.string.bedroom, buttonNumber = 6)
            houseClass.type.bed = toggleNumberRow(stringId = R.string.bed, buttonNumber = 20)
            houseClass.type.baths = toggleNumberRow(stringId = R.string.baths, buttonNumber = 20)
            toggleButtonRow(textStringId = R.string.house_type, navController)
        }
    }
}

@Composable
fun toggleNumberRow(
    stringId: Int,
    buttonNumber: Int,
    modifier: Modifier = Modifier.fillMaxWidth(),
): Int {
    Spacer(modifier = Modifier.padding(10.dp))
    var num by remember { mutableIntStateOf(0) }
    Text(
        modifier = modifier
            .padding(start = 10.dp), text = stringResource(id = stringId) + "：" + num
    )
    Spacer(modifier = Modifier.padding(5.dp))
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
    ) {
        num = numberToggleButtons(length = buttonNumber)
    }
    return num
}

/*
設計了一個多選的button行
第一個標題
第二個按鈕名稱
回傳字串陣列
 */
@SuppressLint("MutableCollectionMutableState")
@Composable
fun toggleButtonRow(
    textStringId: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    val context= LocalContext.current
    var isSelect by remember {
        mutableStateOf(mutableListOf<String>())
    }
    LaunchedEffect(Unit){
        val read= withContext(Dispatchers.IO){
            readDataClass(context = context, key = keyName().key_isSelect, defaultValue = MutableList<String>(1){""})

        }
        withContext(Dispatchers.Main){
            isSelect=read
            Log.i("0123", "讀取後的ISSELECT:$read")
        }
    }
    Spacer(modifier = Modifier.padding(10.dp))
    Column (modifier=modifier){
        Row (modifier = modifier.horizontalScroll(rememberScrollState())) {
            var close by remember {
                mutableStateOf<String>("")
            }
            Text(
                modifier = modifier
                    .padding(start = 10.dp),
                text = stringResource(id = textStringId)+"："
            )
            isSelect.forEach(){ str ->
                val output= HouseTypeStringID.getStringIdByString(str)?:0
                 boxHaveClose(output){
                     close=str
                 }
            }
            if(close!=""){
                Log.i("0123", close)
                val updatedIsSelect = isSelect.toMutableList()
                updatedIsSelect.remove(close)
                isSelect = updatedIsSelect
                saveDataClass(context, keyName().key_isSelect, isSelect)
            }

        }
        Button(modifier=modifier,onClick = { navController.navigate(Screen.SelectInf.name) }) {
            Text(text = stringResource(id = R.string.please_choose))
        }
    }

}

