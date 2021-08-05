package ru.vdv.myapp.mygitapiapp.interfaces

/**
 * Общий интерфейс презентера
 *
 * применим для презентера любого списка , т.к. содержит общие для любого презентера методы
 * @param V  - представляет собой общий тип View для строки списка
 */
interface IListPresenter<V : IItemView> {
    /**
     * Сдушатель клика по элементу списка
     * @property V  - View строки списка
     */
    var itemClickListener: ((V) -> Unit)?

    /**
     * Функция наполнения View
     * @property V  - View строки списка
     */
    fun bindView(view: V)

    /**
     * функция получения количества элементов
     * @return Возвращает количество элементов в списке
     */
    fun getCount(): Int
}