package ru.vdv.myapp.mygitapiapp.interfaces

/**
 * Общий интерфейс списка
 *
 * применим для любого списка, т.к. содержит общие для любого списка свойства
 * @property pos  - позиция элемента списка
 */

interface IItemView {
    var pos: Int
}