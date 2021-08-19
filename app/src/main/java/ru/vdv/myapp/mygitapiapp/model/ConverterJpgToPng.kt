package ru.vdv.myapp.mygitapiapp.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.net.toUri
import io.reactivex.rxjava3.core.Single
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

/**
 * Класс Конвертер файлов изображений из jpg d png
 *
 */

class ConverterJpgToPng(val currentContext: Context) {
    fun convertRx(uri: Uri?): Single<Uri> {
        uri?.let {
            val tempConvertedFile = File.createTempFile("tmpConvert", ".png")
            val fos = FileOutputStream(tempConvertedFile)
            val bos = BufferedOutputStream(fos)
            val mim = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(currentContext.contentResolver, it)
                )
            } else {
                MediaStore.Images.Media.getBitmap(currentContext.contentResolver, it)
            }
            mim.compress(Bitmap.CompressFormat.PNG, 100, bos)
            bos.close()
            fos.close()
            return Single.just(tempConvertedFile.toUri()).delay(3L, TimeUnit.SECONDS)
        }
        return Single.error(Throwable())
    }
}