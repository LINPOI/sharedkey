package com.example.sharedKey.main.things

class SetUrl (member: Member= Member()){
    private val url = "http://192.168.50.29/app/"
    /*
    資料庫的key值
     */
    val account = "Account" to member.account
    val password = "Password" to member.password
    val nickname = "Nickname" to member.nickname
    val name = "UserName" to member.name
    val gmail = "Gmail" to member.gmail
    val phone = "Phone" to member.phone
    val id = "Id" to member.id
    val image = "Image" to imageName.getHouseId()
    val house_id = "House_Id" to imageName.getHouseId()
    val house_name = "House_name" to "your_house_name_here"
    val address = "Address" to "your_address_here"
    fun setMemberNum(choose: Int=0):Pair<String,String> {
        return when (choose) {
            0 -> account
            1 -> password
            2 -> nickname
            3 -> name
            4 -> gmail
            5 -> phone
            6 -> id
            7 -> house_name
            8 -> address
            else -> "" to ""
        }
    }



    /*
    帳號表
    */
    fun getAccountDataUrl(): String {
        return url + "number/allaccount/"
    }

    fun postAccountDataUrl(): String {
        return url+"number/add/"
    }
    fun deleteAccountDataUrl():String{
        return url+"number/deleteaccount/"
    }
    fun postAccountPhoneToSetNewPassword(forget:Boolean):String{
        return if(forget){
            return url + "number/passwordrecovery"
        }else{
            return url + "number/changepassword"
        }
    }
    fun changepassword():String {
        return url + "number/changepassword"
    }
    fun passwordrecovery():String {
        return url + "number/passwordrecovery"
    }
         /*
    用戶表
    */
    fun getUserDataUrl(account:String): String {
        return url + "personaldata/exist=$account"
    }

    fun postUserDataUrl(): String {
        return url + "personaldata/add/"
    }
    fun patchUserDataUrl():String{
        return url + "personaldata/patch/"
    }
    fun postimageUrl():String{
        return url+"personrent/photo/add/"
    }
    /*
    房屋
     */
    fun postHouseDataUrl():String {
        return url + "personrent/data/add/"
    }
    fun getHouseDataUrl():String {
        return url + "personrent/data/search/"
    }

}