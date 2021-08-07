package ru.vdv.myapp.mygitapiapp.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Интерфейс экрана "Информация о пользователе"
 */

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserInfoView: MvpView {
    /**
     * Выводит логин пользователя
     */
    fun showLogin(text: String)
    /**
     * Выводит переданный текст в верхню зарезеркированноу строку информационного листа
     */
    fun showTopString(text: String)
    /**
     * Выводит переданный текст в среднюю зарезеркированноу строку информационного листа
     */
    fun showCenterString(text: String)
    /**
     * Выводит переданный текст в нижнюю зарезеркированноу строку информационного листа
     */
    fun showBottomString(text: String)
}