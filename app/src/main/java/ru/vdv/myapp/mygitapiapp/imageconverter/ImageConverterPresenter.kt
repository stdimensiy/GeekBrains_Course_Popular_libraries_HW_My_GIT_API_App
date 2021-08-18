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
            .convertRx(imageUri)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<Uri> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: Uri?) {
                    if (t != null) {
                        viewState.showConvertedImage(t)
                        viewState.hideProgressBar()
                        viewState.btnAbortConvertDisabled()
                        viewState.signAbortConvertHide()
                        viewState.signWaitingHide()
                    }
                }

                override fun onError(e: Throwable?) {
                    viewState.showErrorBar()
                    viewState.hideProgressBar()
                    viewState.btnAbortConvertDisabled()
                    viewState.signWaitingHide()

                }
            })
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
        viewState.showOriginImage(imageUri)
        viewState.btnStartConvertEnable()
        viewState.signAbortConvertHide()
        viewState.signGetStartedHide()
        viewState.hideErrorBar()
        viewState.signWaitingShow()
    }
}