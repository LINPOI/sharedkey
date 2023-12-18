package com.example.sharedKey.page5_account.page5_3_setHouses

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sharedKey.main.Screen
import com.example.sharedKey.main.things.HandleBackPress
import com.example.sharedKey.main.things.Member
import com.example.sharedKey.main.things.keyName
import com.example.sharedKey.main.things.readDataClass
import com.example.sharedKey.main.things.saveDataClass
import com.example.sharedKey.main.things.toggleButtons
import com.example.test1.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
房屋總類
 */
enum class HouseTypeStringID(val string: String, val stringId: Int) {
    DETACHED_HOUSE("Detached House", R.string.detached_house),
    VILLA("Villa", R.string.villa),
    APARTMENT("Apartment", R.string.apartment),
    MANSION("Mansion", R.string.mansion),
    HING_RISE_BUILDING("High_rise_building", R.string.high_rise_building)

    //新增其他的
    ;

    /*
    .getStringIdByString(string)丟入字串回傳字串id
     */
    companion object {
        fun getStringIdByString(stringToFind: String): Int? {
            val found = values().find { it.string == stringToFind }
            return found?.stringId
        }
    }
}

@Composable
fun SelectInf5_3_1_1(
    navController: NavHostController,
    modifier: Modifier,
    member: Member,
) {
    val context = LocalContext.current
    val mutableList by remember { mutableStateOf(mutableListOf<String>(
        HouseTypeStringID.DETACHED_HOUSE.string,
        HouseTypeStringID.VILLA.string,
        HouseTypeStringID.APARTMENT.string,
        HouseTypeStringID.MANSION.string,
        HouseTypeStringID.HING_RISE_BUILDING.string)) }
    var isSelect by remember { mutableStateOf(mutableListOf<String>()) }
    LaunchedEffect(Unit){
        val read=withContext(Dispatchers.IO){
            readDataClass(context, keyName().key_isSelect, MutableList<String>(1) { "" })
        }
        withContext(Dispatchers.Main) {
            isSelect=read
            Log.i("0123","5-3-1-1讀取:$isSelect")
        }
    }

    Spacer(modifier = Modifier.padding(top = 10.dp))
    Column {
        Row (modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.inversePrimary)
            .border(0.1.dp, MaterialTheme.colorScheme.onBackground)
            .height(50.dp),
        ){
            Icon(
                Icons.Default.ArrowBack, contentDescription = "arrayback",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {  navController.navigate(Screen.HousesInf.name)
                        Log.i("0123", "返回"
                        )}
            )
            Text(
                modifier = modifier.fillMaxWidth().offset((-25).dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                text = stringResource(R.string.select_category),
                fontSize = 35.sp
            )
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            //modifier = modifier.horizontalScroll(rememberScrollState()),
        ) {

            val newSelect = toggleButtons(mutableList,isSelect)
            if(newSelect!=mutableListOf<String>() ){
                saveDataClass(context, keyName().key_isSelect, newSelect)
                Log.i("0123","5-3-1-1newSelect:${newSelect}已保存")
            }
        }
    }


    HandleBackPress {
        Log.i("0123", "返回")
        navController.navigate(Screen.HousesInf.name)
    }
}