package ru.vdv.myapp.mygitapiapp

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.IScreens
import ru.vdv.myapp.mygitapiapp.interfaces.MainView

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}