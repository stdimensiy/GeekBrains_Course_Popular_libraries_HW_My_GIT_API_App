package ru.vdv.myapp.mygitapiapp.imageconverter

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.ImageConverterView
import ru.vdv.myapp.mygitapiapp.model.ConverterJpgToPng
import ru.vdv.myapp.mygitapiapp.model.GithubUsersRepo

class ImageConverterPresenter(
    private val converter: ConverterJpgToPng,
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

    }

    fun btnStartConvertingPressed(imageUri: Uri){
        //нажата кнопка старта конвертации - алгоритм действий:
        //командуем модели начать процесс конвертации модели
        //запускаем прогрессбар
        //командуем модели сделать кнопку ABORT активной
        //получаем данные, командуем представлению выключить прогрессбар
        //командуем представлению отобразить картинку в окне преобразования
        // командуем представлению сделать кнопку abort снова неактивной
        Log.d("Моя проверка - ПРЕЗЕНТЕР / btnConvertImagePressed", "Передан параметр$imageUri")
        viewState.showProgressBar()
        viewState.signWaitingShow()
        viewState.btnAbortConvertEnabled()
        val res = converter.convert(imageUri)
        viewState.showConvertedImage(res)
        viewState.hideProgressBar()
        viewState.signWaitingHide()
        viewState.btnAbortConvertDisabled()
    }

    fun abortConvertImage(){
        converter.stopConverting()
        viewState.btnAbortConvertDisabled()
        viewState.signAbortConvertShow()
    }

    /**
     * Обрабатываемое изображение выбрано
     */
    fun originalImageSelected(imageUri: Uri) {
        viewState.showOriginImage(imageUri)
        viewState.btnStartConvertEnable()
        viewState.signAbortConvertHide()
        viewState.signGetStartedHide()
        viewState.hideErrorBar()
        viewState.signWaitingShow()
    }
}