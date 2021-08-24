package ru.vdv.myapp.mygitapiapp.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.AndroidScreens
import ru.vdv.myapp.mygitapiapp.interfaces.*
import ru.vdv.myapp.mygitapiapp.model.GithubUser

class UsersPresenter(
    private val usersRepo: IGitHubUsersRepo,
    private val schedulers: IMySchedulers,
    private val router: Router
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
            .observeOn(schedulers.main())
            .subscribe(object : SingleObserver<List<GithubUser>> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: List<GithubUser>?) {
                    if (t == null) return
                    onGetUsersSuccess(t)
                }

                override fun onError(e: Throwable?) {
                    onGetUserError(e)
                }
            })
    }

    private fun onGetUsersSuccess(listUsers: List<GithubUser>) {
        viewState.hideProgressBar()
        usersListPresenter.users.addAll(listUsers)
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(AndroidScreens().userInfo(listUsers[itemView.pos].login))
        }
        viewState.updateList()
    }

    private fun onGetUserError(e: Throwable?) {
        viewState.hideProgressBar()
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