
package com.skeleton.android.core.extension

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Environment
import android.support.v4.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException



val Context.networkInfo: NetworkInfo?
    get() =
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

fun Context.getUriFromProvider(bmp: Bitmap): Uri? {
    var bmpUri: Uri? = null
    try {
        val file = File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "share_image_" + System.currentTimeMillis() + ".png")
        val out = FileOutputStream(file)
        bmp.compress(Bitmap.CompressFormat.PNG, 90, out)
        out.close()

        bmpUri = FileProvider.getUriForFile(this, this.packageName + ".fileprovider", file)
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return bmpUri
}