package ru.vdv.myapp.mygitapiapp.interfaces

/**
 * Общий интерфейс списка пользователей
 *
 * Для применения в разных списках пользователя в данном приложения
 * с передачей интерфейса элемента конкретного списка
 */

interface IUserListPresenter : IListPresenter<UserItemView> {
}