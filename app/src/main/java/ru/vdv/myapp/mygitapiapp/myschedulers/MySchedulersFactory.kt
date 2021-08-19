package ru.vdv.myapp.mygitapiapp.myschedulers

import ru.vdv.myapp.mygitapiapp.interfaces.IMySchedulers

object MySchedulersFactory {
    fun create(): IMySchedulers = MySchedulers()
}