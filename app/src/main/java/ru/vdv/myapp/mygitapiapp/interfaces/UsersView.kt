package ru.vdv.myapp.mygitapiapp.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Интерфейс экрана "список пользователей" *
 */

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    /**
     * Первичная инициализация списка, вызывается при присоединении View к Presenter
     */
    fun init()

    /**
     * Обновление содержимого списка
     */
    fun updateList()
}