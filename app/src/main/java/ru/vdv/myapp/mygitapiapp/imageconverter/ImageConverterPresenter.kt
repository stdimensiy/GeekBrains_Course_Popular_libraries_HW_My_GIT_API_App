package ru.vdv.myapp.mygitapiapp.imageconverter

import android.net.Uri
import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.ImageConverterView
import ru.vdv.myapp.mygitapiapp.model.ConverterJpgToPng

class ImageConverterPresenter(
    private val converter: ConverterJpgToPng,
    val router: Router
) : MvpPresenter<ImageConverterView>() {

    var disposables = CompositeDisposable()

    /**
     * Отработка нажатия системной кнопки назад
     */
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

    /**
     * Запуск процесса конвертации изображения
     * @param imageUri - Uri конвертируемого изображения (оригинала)
     */
    fun startConvertingPressed(imageUri: Uri) {
        Log.d("Моя проверка - ПРЕЗЕНТЕР / btnConvertImagePressed", "Передан параметр$imageUri")
        viewState.showProgressBar()
        viewState.signWaitingShow()
        viewState.signGetStartedHide()
        viewState.btnAbortConvertEnabled()
        converter
            .convertRx(null)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Uri> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: Uri?) {
                    if (t != null) {
                        onConvertingSuccess(t)
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.d("Моя проверка - ПРЕЗЕНТЕР / btnConvertImagePressed", "Поймал ошибку!!!")
                    onConvertingError(e)
                }
            })
    }

    /**
     * Обработка успеха от источника
     */
    private fun onConvertingSuccess(uri: Uri) {
        Log.d("Моя проверка", "Вызван модуль отображения успеха")
        viewState.showConvertedImage(uri)
        viewState.hideProgressBar()
        viewState.btnAbortConvertDisabled()
        viewState.signAbortConvertHide()
        viewState.signWaitingHide()
    }

    /**
     * Обработка ошибки от источника
     */
    private fun onConvertingError(e: Throwable?) {
        Log.d("Моя проверка", "Вызван модуль отображения ошибки  $e")
        viewState.showErrorBar()
        viewState.hideProgressBar()
        viewState.btnAbortConvertDisabled()
        viewState.signWaitingHide()
    }


    /**
     * Прерывание процесса конвертации изображения
     * фактически отписывается от источника, и выводит зарезервированное изображение на экран
     * попутно скрывает прогресс бар и деактивирует кнопку прерывания процесса конвертации
     */
    fun abortConvertImagePressed() {
        disposables.dispose()
        disposables = CompositeDisposable()
        viewState.hideProgressBar()
        viewState.signWaitingHide()
        viewState.btnAbortConvertDisabled()
        viewState.signAbortConvertShow()
    }

    /**
     * Обрабатываемое изображение выбрано
     * @param imageUri - Uri конвертируемого изображения (оригинала)
     * отдает команду вывести выбранное изображение в виджет оригинала
     * попутно активирует кнопку старта процесса конвертации изображения
     * скрывает ненужные сигнумы и отображает картинку заглушку ожидания результата конвертации
     */
    fun originalImageSelected(imageUri: Uri) {
        Log.d(
            "Моя проверка",
            "Отрабатываю originalImageSelected с параметром" + imageUri.toString()
        )
        viewState.showOriginImage(imageUri)
        viewState.btnStartConvertEnable()
        viewState.signAbortConvertHide()
        viewState.signGetStartedHide()
        viewState.hideErrorBar()
        viewState.signWaitingShow()
    }
}