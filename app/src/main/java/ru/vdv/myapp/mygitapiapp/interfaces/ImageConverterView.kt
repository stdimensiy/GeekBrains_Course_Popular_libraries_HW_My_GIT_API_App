package ru.vdv.myapp.mygitapiapp.interfaces

import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Интерфейс экрана "Конвертер файлов изображений jpg -> png"
 */

@StateStrategyType(AddToEndSingleStrategy::class)
interface ImageConverterView : ProgressView, ErrorView, MvpView {
    /**
     * Первичная инициализация элементов View при присоединении к Presenter
     */
    fun init()

    /**
     * Выводит исходное изображение
     */
    fun showOriginImage(uri: Uri)

    /**
     * Выводит конвертированное изображение
     */
    fun showConvertedImage(uri: Uri)

    /**
     * Выводит сообщение
     */
    fun showMessage(text: String)

    /**
     * Делает кнопку старта процесса конвертации активной
     */
    fun btnStartConvertEnable()

    /**
     * Деактивирует кнопку старта процесса конвертации
     */
    fun btnStartConvertDisabled()

    /**
     * Делает кнопку отмены процесса конвертации активной
     */
    fun btnAbortConvertEnabled()

    /**
     * Деактивирует кнопку отменты процесса конвертации
     */
    fun btnAbortConvertDisabled()

    /**
     * Показывает заглушку - флажок сигнализирующий о прерывании конвертации
     */
    fun signAbortConvertShow()

    /**
     * Скрывает заглушку - флажок сигнализирующий о прерывании конвертации
     */
    fun signAbortConvertHide()

    /**
     * Показывает заглушку - флажок призывающий начать чтото делать уже
     */
    fun signGetStartedShow()

    /**
     * Скрывает заглушку - флажок призывающий начать чтото делать...
     */
    fun signGetStartedHide()

    /**
     * Показывает заглушку - флажок ожидания действия...
     */
    fun signWaitingShow()

    /**
     * Скрывает заглушку - флажок ожидания действия...
     */
    fun signWaitingHide()
}