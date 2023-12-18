package com.example.sharedKey.main.things

import com.example.sharedKey.page5_account.page5_3_setHouses.things.Type

data class HouseClass(
    var picture: Int=0,
    var name:String="",
    var type: Type =Type(),
    var address:String="",
    var Safety_Facilities:MutableList<String>?=null,
    var additionally_provided:MutableList<String>?=null,
    var member: Member=Member(),
    )