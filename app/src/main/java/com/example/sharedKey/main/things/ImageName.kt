package com.example.sharedKey.main.things

class ImageName {

    private var photo_Name:String=""
    private var house_Id:Int=0
    val avatar="user_avatar.png"
    val housePicture:(Int,String)->String={int,string->
        house_Id=int
        photo_Name=string
        "housePicture${house_Id}_${photo_Name}.png"
    }
    fun getHouseId():Int{
        return house_Id
    }
}
var imageName:ImageName=ImageName()