package ru.vdv.myapp.mygitapiapp.imageconverter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.ImageConverterView

class ImageConverterPresenter(
    val router: Router
) : MvpPresenter<ImageConverterView>() {
    val disposables = CompositeDisposable()
    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }
}