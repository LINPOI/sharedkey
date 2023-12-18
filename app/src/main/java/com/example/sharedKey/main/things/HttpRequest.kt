package com.example.sharedKey.main.things

import android.os.Handler
import android.os.Looper
import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.IOException
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

fun arrayString(result:Any):Boolean{
    return result is Array<*> && result.isArrayOf<String>()
}
//設定url位置

//接口實現:在其他函數使用這個函數時要實現接口裡面的東西
interface MemberCheckCallback {
    fun onCheckResult(result: Any)
}



//新增
fun post(putKeyValue: Map<String, Any>, byteArray: ByteArray?=null,url: String, callback: MemberCheckCallback?=null){
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/main/things/HttpRequest.kt:125:16")
    val client = OkHttpClient()
    Log.i("0123", "post$putKeyValue,bytearray=$byteArray,url=$url")
    // 定義要發送請求的 URL
    // 構建 JSON 數據
    val json = JSONObject()
    for ((key, value) in putKeyValue) {
        json.put(key, value)
    }

    val requestBody =
        if(byteArray!=null){
        MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "json",
                null,
                json.toString().toRequestBody("application/json".toMediaTypeOrNull())
            )

            .addFormDataPart(
                "image",//key值
                "image.png",
                "byteArray".toRequestBody("image/*".toMediaTypeOrNull())
            )
            .build()
    }else{
        json.toString().toRequestBody("application/json;charset=utf-8".toMediaType())
    }
    // 創建一個 HTTP 請求，使用指定的 URL
    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()

    // 使用 OkHttpClient ，回應非同步執行 HTTP 請求，並設置回調函數
    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                // 在這裡可以進一步處理回應數據
                Log.i("0123", "post:成功發送數據$putKeyValue")
                Handler(Looper.getMainLooper()).post {
                    callback?.onCheckResult(1)
                }
            }else {
                // 處理不成功的回應
                Log.e("0123", "post:回應不成功，代碼：" + response.code)
                Handler(Looper.getMainLooper()).post {
                    callback?.onCheckResult(0)
                }
                // 記錄錯誤信息
                if (response.body != null) {
                    Log.e("0123", "post，錯誤內容：" + response.body?.string())
                    Handler(Looper.getMainLooper()).post {
                        callback?.onCheckResult(0)
                    }
                }
            }
        }

        override fun onFailure(call: Call, e: IOException) {
            // 當請求失敗時，處理錯誤情況
            Log.e("0123", "post，請求失敗：" + e.message)
            callback?.onCheckResult(0)
            e.printStackTrace()
        }
    })
}
//刪除
fun delete(putKeyValue: Map<String, Any>, url: String) {
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/main/things/HttpRequest.kt:292:16")
    val client = OkHttpClient()
    Log.i("0123", "delete:成功進入")
    val json = JSONObject(putKeyValue)

    val requestBody =json.toString().toRequestBody("application/json;charset=utf-8".toMediaType())
    val request = Request.Builder()
        .url(url)
        .delete(requestBody)// 刪除
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                Log.i("0123","delete，請求成功:已註銷")
            }else {
                // 處理不成功的回應
                Log.e("0123", "delete:回應不成功，代碼：" + response.code)

                // 記錄錯誤信息
                if (response.body != null) {
                    Log.e("0123", "delete，錯誤內容：" + response.body?.string())
                }
            }
        }

        override fun onFailure(call: Call, e: IOException) {
            Log.i("0123","delete，請求失敗")
            e.printStackTrace()
        }
    })
}
//修改
fun patch(putKeyValue: Map<String, Any>, url: String, callback: MemberCheckCallback?=null) {
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/main/things/HttpRequest.kt:174:16")
    val client = OkHttpClient()
    Log.i("0123", "updatePassword:成功進入")
    //創建json進行更新動作
    val json = JSONObject(putKeyValue)
    //mapOf( Seturl(member).account,Seturl(member).password)
    //mapOf(Seturl(member).nickname,Seturl(member).name,Seturl(member).gmail,Seturl(member).phone,Seturl(member).id)
    val requestBody = json.toString().toRequestBody("application/json;charset=utf-8".toMediaType())

    val request = Request.Builder()
        .url(url)
        .patch(requestBody) // 使用 PATCH 部分更新請求
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {


            if (response.isSuccessful) {
                Log.i("0123", "updatePassword請求成功:$putKeyValue")
                callback?.onCheckResult(1)
            } else {
                callback?.onCheckResult(2)
            }
        }

        override fun onFailure(call: Call, e: IOException) {
            callback?.onCheckResult(0)
            Log.i("0123", "updatePassword請求失敗")
            e.printStackTrace()
        }
    })
}

//查詢

//在單體資料中找尋
fun getForOne(member: Member, url:String, callback: MemberCheckCallback){
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/main/things/HttpRequest.kt:28:16")
    //0失敗
    //1成功

    val client = OkHttpClient()
    Log.i("0123", "getUserData:$url")
    val request = Request.Builder()
        .url(url)// url 網址
        .get()
        .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val responseBody = response.body?.string()

                try {
                    val jsonResponse = JSONObject(responseBody ?: "")
                    val userArray = jsonResponse.optJSONArray("查詢成功")

                    if (userArray != null && userArray.length() > 0) {
                        val userObject = userArray.getJSONObject(0)
                        Log.i("0123", "get:$userObject")
                        val valuesArray = mutableListOf<String>()
                        val keys = userObject.names()
                        if (keys != null) {
                            for (i in 0 until keys.length()) {//鍵值數量
                                val key = keys.getString(i)//哪一個鍵值
                                val value = userObject.optString(key, "")//鍵值裡面的值
                                valuesArray.add(value)
                            }
                        }
//                        name = userObject.optString("Nickname", "")
//                         = userObject.optString("Username", "")
//                        l = userObject.optString("Gmail", "")
//                        e = userObject.optString("Phone", "")
//                         userObject.optString("Id", "")
                        Handler(Looper.getMainLooper()).post {
                            callback.onCheckResult(valuesArray.toTypedArray())
                        }
                        return
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            // 若沒有成功取得資料或發生錯誤，通知回調函數
            Handler(Looper.getMainLooper()).post {//跟隨線程，才能使用showtoast等
                callback.onCheckResult(0)
            }
        }
        override fun onFailure(call: Call, e: IOException) {
            // 當請求失敗時，處理錯誤情況
            Log.e("0123", e.toString())
            Handler(Looper.getMainLooper()).post {
                callback.onCheckResult(0)
            }
        }
    })
}
//在全部資料中尋找
fun getForAll(member: Member, url: String, callback: MemberCheckCallback) {
    logaddress("C:/Users/user/AndroidStudioProjects/sharedkey/app/src/main/java/com/example/sharedKey/main/things/HttpRequest.kt:295:16")
    //0帳號不存在
    //1登入成功
    //2密碼錯誤
    // 創建一個 OkHttpClient 客戶端實例
    val client = OkHttpClient()
    Log.i("0123", "url=$url")
    // 定義要發送請求的 URL
    // 構建 JSON 數據
    // 創建一個 HTTP 請求，使用指定的 URL
    val request = Request.Builder()
        .url(url)// url 網址
        .get()
        .build()
    // 使用 OkHttpClient 非同步執行 HTTP 請求，並設置回調函數
    client.newCall(request).enqueue(object : Callback {

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                // 當請求成功時，處理伺服器回應數據
                val responseBody = response.body?.string()
                val jsonArray = JSONArray(responseBody) // 將回應轉換為 JSON 數組
                for (i in 0 until jsonArray.length()) {//資料庫陣列長度，一個個找資料

                    val userObject = jsonArray.getJSONObject(i)
                    Log.i("0123","$userObject")
                    val jName = userObject.optString("Account", "")
                    val jPassword = userObject.optString("Password", "")
                    if (jName == member.account) {//尋找帳號正確
                        if(jPassword == member.password){//密碼正確
                            Log.i("0123", "checkMemberdata:登入帳號:${member.account}" +
                                    "密碼:${member.password}")

                            Handler(Looper.getMainLooper()).post {
                                callback.onCheckResult(1)
                            }
                        }else{
                            Handler(Looper.getMainLooper()).post {
                                callback.onCheckResult(2)
                            }
                        }


                        return // 找到使用者後，不再繼續搜索
                    }
                }

                Handler(Looper.getMainLooper()).post {
                    callback.onCheckResult(0)
                }//沒有找到，使用了同步處理及布林解決1跟0同時出現問題


            }
        }
        override fun onFailure(call: Call, e: IOException) {
            // 當請求失敗時，處理錯誤情況
            Log.e("MycheckTeg", e.toString())
        }
    })
}