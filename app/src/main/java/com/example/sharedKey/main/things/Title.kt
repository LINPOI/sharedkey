package com.example.sharedKey.main.things

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Title (text:Int,othertext:String="",modifier: Modifier=Modifier){
    Text(text =othertext+ stringResource(id = text),
        modifier
            .background(MaterialTheme.colorScheme.inversePrimary)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge)

}