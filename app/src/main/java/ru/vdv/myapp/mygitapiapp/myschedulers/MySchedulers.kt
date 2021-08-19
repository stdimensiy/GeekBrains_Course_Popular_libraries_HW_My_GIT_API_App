package ru.vdv.myapp.mygitapiapp.myschedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.vdv.myapp.mygitapiapp.interfaces.IMySchedulers

/**
 * Метод реализации планировщиков
 * в данном классе чуть больше методов чем требуется для выполнения конкретного ДЗ
 * дополнительные методы внедрены для самоподготовки и тренировки.
 * описание методов и конкретных планировщиков приведены в интерфейсе
 */

class MySchedulers : IMySchedulers {
    override fun main(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun newThread(): Scheduler = Schedulers.newThread()

    override fun single(): Scheduler = Schedulers.single()

    override fun trampoline(): Scheduler = Schedulers.trampoline()

    override fun start() {
        Schedulers.start()
    }

    override fun shutdown() {
        Schedulers.shutdown()
    }
}