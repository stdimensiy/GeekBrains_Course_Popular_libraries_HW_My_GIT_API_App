package ru.vdv.myapp.mygitapiapp.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Общий интерфейс главного окна, после приведения приложения к Single-Activity и реализации cicerone
 * остался фактически пустым. Дальнейшая судьба данного интерфейса должна быть решена в рамках
 * выполнения ДЗ
 */

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView