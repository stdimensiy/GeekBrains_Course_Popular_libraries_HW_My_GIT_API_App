package ru.vdv.myapp.mygitapiapp.interfaces

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Общий интерфейс прогрессбара
 *
 * Добавляется к фрагментам где нужно будет отображать состояние загрузки  / обновления данных
 */

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