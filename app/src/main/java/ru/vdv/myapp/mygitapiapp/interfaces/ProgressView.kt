package ru.vdv.myapp.mygitapiapp.interfaces

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProgressView {
    /**
     * Показывает прогрессбар
     */
    fun showProgressBar()
    /**
     * Скрывает прогрессбар
     */
    fun hideProgressBar()
}