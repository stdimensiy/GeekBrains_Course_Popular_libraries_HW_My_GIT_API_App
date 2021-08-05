package ru.vdv.myapp.mygitapiapp

import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.IUserListPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.MainView
import ru.vdv.myapp.mygitapiapp.interfaces.UserItemView
import ru.vdv.myapp.mygitapiapp.model.GithubUser
import ru.vdv.myapp.mygitapiapp.model.GithubUsersRepo

class MainPresenter(val usersRepo: GithubUsersRepo) : MvpPresenter<MainView>() {

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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            //TODO: переход на экран пользователя
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
}
