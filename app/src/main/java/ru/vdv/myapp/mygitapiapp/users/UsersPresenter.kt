package ru.vdv.myapp.mygitapiapp.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.AndroidScreens
import ru.vdv.myapp.mygitapiapp.interfaces.IUserListPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.UserItemView
import ru.vdv.myapp.mygitapiapp.interfaces.UsersView
import ru.vdv.myapp.mygitapiapp.model.GithubUser
import ru.vdv.myapp.mygitapiapp.model.GithubUsersRepo

class UsersPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()
    val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.d("Моя проверка", "Сработал onFirstViewAttach")
        viewState.init()
        viewState.showProgressBar()
        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<GithubUser>> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: List<GithubUser>?) {
                    Log.d("Моя проверка", "Сработал")
                    if (t != null) {
                        viewState.hideProgressBar()
                        usersListPresenter.users.addAll(t)
                        usersListPresenter.itemClickListener = { itemView ->
                            router.navigateTo(AndroidScreens().userInfo(t[itemView.pos].id))
                        }
                        Log.d("Моя проверка", usersListPresenter.users.size.toString())
                        viewState.updateList()
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.d("Моя проверка", "Ошибка")
                }
            })

        //loadData()
    }

//    fun loadData() {
//        val users = usersRepo.getUsers()
//        usersListPresenter.users.addAll(users)
//        usersListPresenter.itemClickListener = { itemView ->
//            router.navigateTo(AndroidScreens().userInfo(users[itemView.pos].id))
//        }
//        viewState.updateList()
//    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        Log.d("Моя проверка", "Зачистка")
        disposables.clear()
    }
}