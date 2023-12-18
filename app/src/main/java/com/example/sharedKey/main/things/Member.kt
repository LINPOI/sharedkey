package com.example.sharedKey.main.things


//會員資料設置
data class Member(
    var account: String = "",
    var password: String = "",
    var nickname: String ="",
    var name:String="",
    var gmail:String=account,
    var phone:String="",
    var id:String="",

)
