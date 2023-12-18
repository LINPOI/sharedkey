package com.example.sharedKey.main.things

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore

private const val REQUEST_CODE_GALLERY = 1001
private const val REQUEST_CODE_CAMERA = 1002

// openGallery
fun openGallery(activity: Activity) {
    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    activity.startActivityForResult(intent, REQUEST_CODE_GALLERY)
}

// openCamera
fun openCamera(activity: Activity) {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    activity.startActivityForResult(intent, REQUEST_CODE_CAMERA)
}

// handleImageResult
fun handleImageResult(requestCode: Int, resultCode: Int, data: Intent?, callback: (Uri?, Bitmap?) -> Unit) {
    if (resultCode == Activity.RESULT_OK) {
        when (requestCode) {
            REQUEST_CODE_GALLERY -> {
                val imageUri: Uri? = data?.data
                callback(imageUri, null)
            }
            REQUEST_CODE_CAMERA -> {
                val imageBitmap: Bitmap? = data?.extras?.get("data") as Bitmap?
                callback(null, imageBitmap)
            }
        }
    }
}