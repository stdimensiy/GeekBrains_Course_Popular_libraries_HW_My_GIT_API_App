package ru.vdv.myapp.mygitapiapp.userInfo

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.UserInfoView
import ru.vdv.myapp.mygitapiapp.model.GithubUsersRepo

class UserInfoPresenter(
    val userId: Int? = null,
    private val githubUsersRepo: GithubUsersRepo,
    val router: Router
) : MvpPresenter<UserInfoView>() {

//    override fun onFirstViewAttach() {
//        val currentUser = githubUsersRepo.getUsers().firstOrNull { it.id == userId }
//        currentUser?.let { viewState.showLogin(it.login) }
//        currentUser?.let { viewState.showTopString("Заглушка верхей строки")
//            (it.login) }
//        currentUser?.let { viewState.showCenterString(it.htmlUrl) }
//        currentUser?.let { viewState.showBottomString("Заглушка нижней строки") }
//    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}