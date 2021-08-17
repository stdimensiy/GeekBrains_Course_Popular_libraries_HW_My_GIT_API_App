package ru.vdv.myapp.mygitapiapp.interfaces

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
    fun showOriginImage(pathImageFile: String)

    /**
     * Выводит конвертированное изображение
     */
    fun showConvertedImage(pathImageFile: String)

    /**
     * Выводит сообщение
     */
    fun showMessage(text: String)
}