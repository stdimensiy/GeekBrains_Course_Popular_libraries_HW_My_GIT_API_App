package ru.vdv.myapp.mygitapiapp.interfaces

/**
 * Интерфейс элементов списка Users
 */

interface UserItemView : IItemView {
    /**
     * Метод устанавливает значение текстового поля text
     * @param text  - передаваемая для отображения строка
     */
    fun setLogin(text: String)

    /**
     * Метод устанавливает картинку аватара пользователя
     * @param url  - адрес (URL) изображения (аватара пользователя)
     */
    fun setImageAvatar(url: String)
}