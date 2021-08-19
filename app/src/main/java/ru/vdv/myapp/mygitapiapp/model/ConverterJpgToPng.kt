package ru.vdv.myapp.mygitapiapp.model

import android.content.Context
import android.net.Uri
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.core.net.toUri
import io.reactivex.rxjava3.core.Single
import ru.vdv.myapp.mygitapiapp.R
import ru.vdv.myapp.mygitapiapp.R.string.error_message_not_uri
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.concurrent.TimeUnit

/**
 * Класс Конвертер файлов изображений из jpg d png
 *
 */

class ConverterJpgToPng(val currentContext: Context) {
    private val TAG = "VDV_ConverterJpgToPng"

    fun convertRx(uri: Uri?): Single<Uri> {
        Log.d(TAG, "Нажата кнопка старта конвертации в режиме RX")
        uri?.let {
            val tempConvertedFile = File.createTempFile("tmpConvert", ".png")
            Log.d(TAG, "временный файл создан$tempConvertedFile")
            val fos = FileOutputStream(tempConvertedFile)
            val bos = BufferedOutputStream(fos)
            val mim = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Log.d(TAG, "Сработал НОВЫЙ метод")
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(currentContext.contentResolver, it))
            } else {
                Log.d(TAG, "Сработал старый метод")
                MediaStore.Images.Media.getBitmap(currentContext.contentResolver, it)
            }
            mim.compress(Bitmap.CompressFormat.PNG, 100, bos)
            bos.close()
            fos.close()
            Log.d(TAG, "Результат RX" + tempConvertedFile.toUri())
            return Single.just(tempConvertedFile.toUri()).delay(5L, TimeUnit.SECONDS)
        }
        return Single.error(Throwable())
    }
}