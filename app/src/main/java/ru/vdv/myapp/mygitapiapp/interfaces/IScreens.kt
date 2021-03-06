package ru.vdv.myapp.mygitapiapp.interfaces

import com.github.terrakok.cicerone.Screen

/**
 * Общий интерфейс экранов
 *
 * организационный модуль, в котором принципиально объявляются экраны приложения
 */
interface IScreens {
    /**
     * Экран списка пользователей (главный)
     */
    fun users(): Screen

    /**
     * Экран детализации информации о пользователе
     */
    fun userInfo(userLogin: String): Screen

    /**
     * Экран детализации информации о репозитории
     */
    fun repoInfo(repositoryName: String): Screen

    /**
     * Экран конвертации изображений
     */
    fun imageConverter(): Screen
}