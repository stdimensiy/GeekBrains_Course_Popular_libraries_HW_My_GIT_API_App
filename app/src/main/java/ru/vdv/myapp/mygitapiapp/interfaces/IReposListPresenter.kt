package ru.vdv.myapp.mygitapiapp.interfaces
/**
 * Общий интерфейс списка репозиториев
 *
 * Для применения в разных списках репозиториев в данном приложения
 * с передачей интерфейса элемента конкретного списка
 */

interface IReposListPresenter : IListPresenter<RepoItemView> {
}