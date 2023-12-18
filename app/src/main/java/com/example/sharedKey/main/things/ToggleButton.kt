package com.example.sharedKey.main.things

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sharedKey.page5_account.page5_3_setHouses.HouseTypeStringID

/*
簡單的ToggleButton
 */
@SuppressLint("MutableCollectionMutableState")
@Composable
fun toggleButtons(
    mutableList: MutableList<String>,
    mutableList2: MutableList<String>,
): MutableList<String> {
    var output by remember { mutableStateOf(MutableList<String>(1){""}) }
    output=mutableList2
    Log.i("0123","output=$output")
    mutableList.forEach {
        var isChecked by remember { mutableStateOf(false) }
        output.forEach{it2->
            if(it==it2) {
                isChecked = true
            }
            return@forEach
        }
        val stringId = HouseTypeStringID.getStringIdByString(it) ?: 0
        Spacer(modifier = Modifier.padding(5.dp))

        Box(
            modifier = Modifier
                .height(40.dp)
                //.clip(RoundedCornerShape(10.dp))
                .background(

                    if (isChecked) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.inversePrimary
                )
                .border(0.1.dp, Color.Black, RectangleShape)
                .padding(5.dp)
                .clickable {
                    isChecked = !isChecked
                    if (isChecked) output.add(it) else output.remove(it)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = stringId),
            )
        }

    }
    output.forEach() {
        Log.i("0123", it)
    }
    return output
}

@Composable
fun boxHaveClose(text: Int = 0, isClick:()->Unit) {
    Box(
        modifier = Modifier
            .height(40.dp)
            //.clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.inversePrimary)
            .border(0.1.dp, Color.Black, RectangleShape)
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Text(
                text = stringResource(text),
            )
            Box(
                modifier = Modifier
                    .clickable { isClick() }
            ) {

                Icon(Icons.Default.Clear, contentDescription = null)
            }

        }

    }

}

@Composable
fun toggleButton(text: Int = 0): String? {
    var isChecked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .height(40.dp)
            //.clip(RoundedCornerShape(10.dp))
            .background(
                if (isChecked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary
            )
            .border(0.1.dp, Color.Black, RectangleShape)
            .padding(5.dp)
            .clickable {
                isChecked = !isChecked
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(text),
        )
    }
    return if (isChecked) stringResource(text) else null
}

/*
給長度列出多個單選BOX [][][]
 */
@Composable
fun numberToggleButtons(length: Int): Int {
    var isChecked by remember { mutableIntStateOf(0) }
    val array = Array<Int>(length) { it }
    array.forEach { value ->
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .background(if (value == isChecked) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.background)
                .border(0.1.dp, MaterialTheme.colorScheme.onBackground, RectangleShape)
                .padding(5.dp)
                .clickable {
                    isChecked = value
                },
        ) {
            Text(
                text ="${value + 1}"+ if (array[value] == array.size - 1) "+" else "",
                modifier = Modifier.align(Alignment.Center),
            )
        }

    }
    return isChecked + 1
}