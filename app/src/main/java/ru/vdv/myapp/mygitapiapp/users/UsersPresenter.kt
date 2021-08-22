package ru.vdv.myapp.mygitapiapp.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.AndroidScreens
import ru.vdv.myapp.mygitapiapp.interfaces.IGitHubUsersRepo
import ru.vdv.myapp.mygitapiapp.interfaces.IUserListPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.UserItemView
import ru.vdv.myapp.mygitapiapp.interfaces.UsersView
import ru.vdv.myapp.mygitapiapp.model.GithubUser

class UsersPresenter(
    val usersRepo: IGitHubUsersRepo,
    val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login.let {
                view.setLogin(user.login)
                view.setImageAvatar(user.avatarUrl)
            }
        }
    }

    val usersListPresenter = UsersListPresenter()
    val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showProgressBar()
        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<GithubUser>> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: List<GithubUser>?) {
                    if (t != null) {
                        Log.d("Моя проверка в презентере", "Успех = " + t.size)
                        viewState.hideProgressBar()
                        usersListPresenter.users.addAll(t)
                        usersListPresenter.itemClickListener = { itemView ->
                            router.navigateTo(AndroidScreens().userInfo(t[itemView.pos].login))
                        }
                        viewState.updateList()
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.d("Моя проверка в презентере", "Ошибка")
                    viewState.hideProgressBar()
                }
            })
    }

    fun goToImageConverter() {
        router.navigateTo(AndroidScreens().imageConverter())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }
}