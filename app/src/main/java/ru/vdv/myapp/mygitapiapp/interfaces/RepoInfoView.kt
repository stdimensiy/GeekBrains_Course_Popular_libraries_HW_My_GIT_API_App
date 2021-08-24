package ru.vdv.myapp.mygitapiapp.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Интерфейс экрана "Информация о пользователе"
 */

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoInfoView : ProgressView, ErrorView, MvpView {
    /**
     * Выводит логин пользователя
     */
    fun showLogin(text: String)

    /**
     * Метод устанавливает картинку аватара пользователя
     * @param url  - адрес (URL) изображения (аватара пользователя)
     */
    fun setImageAvatar(url: String)

    /**
     * Выводит переданный текст в поле наименования репозитория
     */
    fun showNameRepository(text: String)

    /**
     * Выводит переданный текст в поле описания репозитория
     */
    fun showDescriptionRepository(text: String)

    /**
     * Выводит переданное значение количества форков
     */
    fun showCountFork(count: String)

    /**
     * Первичная инициализация вью фрагмента, вызывается при присоединении View к Presenter
     */
    fun init()
}