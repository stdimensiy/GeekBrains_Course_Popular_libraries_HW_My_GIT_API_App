package ru.vdv.myapp.mygitapiapp.interfaces

/**
 * Универсальный интерфейс, на случай операивной замены библиотеки работающей с изображениями
 */

interface IImageLoader<T> {
    fun loadInfo(url: String, container: T)
}