package ru.vdv.myapp.mygitapiapp.imageconverter

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.ImageConverterView

class ImageConverterPresenter(
    val router: Router
) : MvpPresenter<ImageConverterView>() {
    val disposables = CompositeDisposable()
    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    override fun onDestroy() {
        disposables.clear()
    }

    fun selectImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        val PICK_IMAGE: Intent? = null
//        result
//        onActivityResult()startActivityForResult(intent, 1000);

    }

    fun convertImage(imageUri: Uri){
        Log.d("Моя проверка", "Передан параметр$imageUri")

    }

    fun abortConvertImage(){

    }
}