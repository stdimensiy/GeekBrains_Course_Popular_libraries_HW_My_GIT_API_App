package ru.vdv.myapp.mygitapiapp.interfaces

/**
 * Общий интерфейс слушателя кнопки возврата
 *
 * Планируется реализация в любом фрагменте, где предусмотрена возможность возврата к предыдущему
 * экрану по системной кнопке "назад"
 * @return Boolean - нажатия кнопки
 */
interface BackButtonListener {
    fun backPressed(): Boolean
}