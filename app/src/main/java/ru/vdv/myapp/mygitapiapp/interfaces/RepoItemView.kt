package ru.vdv.myapp.mygitapiapp.interfaces

/**
 * Интерфейс элементов списка репозиториев
 */

interface RepoItemView : IItemView {
    /**
     * Метод устанавливает значение текстового поля краткого наименования репозитория
     * @param text  - передаваемая для отображения строка
     */
    fun setName(text: String)

    /**
     * Метод устанавливает значение текстового поля описания репозитория
     * @param text  - передаваемая для отображения строка
     */
    fun setDescription(text: String)
}
