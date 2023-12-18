package com.example.sharedKey.main.things

import android.util.Log
val tag0123="0123"
val tag="Logaddress"
fun Logaddress(address:String,msg:String=""){
    Log.i(tag0123,"used->$msg" )
    Log.i(tag,"used->$address")
}
fun logaddress(address:String,msg:String=""){
    val tag="address"
    Log.i(tag,"used->$address")
}
