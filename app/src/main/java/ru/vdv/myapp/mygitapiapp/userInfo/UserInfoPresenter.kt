package ru.vdv.myapp.mygitapiapp.userInfo

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.UserInfoView
import ru.vdv.myapp.mygitapiapp.model.GithubUserAdvanced
import ru.vdv.myapp.mygitapiapp.model.GithubUsersRepo

class UserInfoPresenter(
    val userId: Int? = null,
    private val githubUsersRepo: GithubUsersRepo,
    val router: Router
) : MvpPresenter<UserInfoView>() {
    val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        if (userId != null) {
            viewState.showProgressBar()
            githubUsersRepo
                .getUserById(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<GithubUserAdvanced> {
                    override fun onSubscribe(d: Disposable?) {
                        disposables.add(d)
                    }

                    override fun onSuccess(t: GithubUserAdvanced?) {
                        Log.d("Моя проверка", "Сработал")
                        if (t != null) {
                            t.let { viewState.showLogin(it.login) }
                            viewState.showTopString("Заглушка верхей строки")
                            t.let { viewState.showCenterString(it.htmlUrl) }
                            viewState.showBottomString("Заглушка нижней строки")
                            viewState.hideProgressBar()
                            Log.d("Моя проверка", t.toString())
                        }
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("Моя проверка", "Ошибка")
                    }
                })
        }


//        val currentUser = githubUsersRepo.getUsers().firstOrNull { it.id == userId }
//        currentUser?.let { viewState.showLogin(it.login) }
//        currentUser?.let { viewState.showTopString("Заглушка верхей строки")
//            (it.login) }
//        currentUser?.let { viewState.showCenterString(it.htmlUrl) }
//        currentUser?.let { viewState.showBottomString("Заглушка нижней строки") }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        Log.d("Моя проверка", "Зачистка")
        disposables.clear()
    }
}