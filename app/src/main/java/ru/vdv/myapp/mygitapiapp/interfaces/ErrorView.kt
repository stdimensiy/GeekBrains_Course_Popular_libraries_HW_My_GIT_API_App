package ru.vdv.myapp.mygitapiapp.interfaces

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ErrorView {
    /**
     * Показывает виджет ошибки
     */
    fun showErrorBar()

    /**
     * Скрывает виджет ошибки
     */
    fun hideErrorBar()
}