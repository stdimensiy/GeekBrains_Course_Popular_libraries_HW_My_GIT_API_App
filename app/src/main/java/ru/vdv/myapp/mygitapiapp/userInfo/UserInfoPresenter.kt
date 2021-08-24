package ru.vdv.myapp.mygitapiapp.userInfo

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.AndroidScreens
import ru.vdv.myapp.mygitapiapp.interfaces.IMySchedulers
import ru.vdv.myapp.mygitapiapp.interfaces.IReposListPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.RepoItemView
import ru.vdv.myapp.mygitapiapp.interfaces.UserInfoView
import ru.vdv.myapp.mygitapiapp.model.GithubUserAdvanced
import ru.vdv.myapp.mygitapiapp.model.Repository
import ru.vdv.myapp.mygitapiapp.model.RetrofitGitHubUserRepo

class UserInfoPresenter(
    private val userLogin: String? = null,
    private val githubUsersRepo: RetrofitGitHubUserRepo,
    private val schedulers: IMySchedulers,
    private val router: Router
) : MvpPresenter<UserInfoView>() {
    private val disposables = CompositeDisposable()

    class ReposListPresenter : IReposListPresenter {
        val repositories = mutableListOf<Repository>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null
        override fun getCount() = repositories.size

        override fun bindView(view: RepoItemView) {
            val repos = repositories[view.pos]
            repos.name.let {
                view.setName(repos.name)
                repos.description?.let { view.setDescription((repos.description)) }
            }
        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        if (userLogin != null) {
            viewState.init()
            viewState.hideErrorBar()
            viewState.showProgressBar()
            githubUsersRepo
                .getUserByLogin(userLogin)
                .observeOn(schedulers.main())
                .subscribe(object : SingleObserver<GithubUserAdvanced> {
                    override fun onSubscribe(d: Disposable?) {
                        disposables.add(d)
                    }

                    override fun onSuccess(userInfo: GithubUserAdvanced?) {
                        userInfo?.let {
                            onGetUserByLoginSuccess(it)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        onGetUserByLoginError(e)
                    }
                })
        }
    }

    fun onGetUserByLoginSuccess(userInfo: GithubUserAdvanced) {
        viewState.showLogin(userInfo.login)
        viewState.setImageAvatar(userInfo.avatarUrl)
        viewState.showTopString("Заглушка верхей строки")
        githubUsersRepo
            .getUserRepos(userInfo.login, null, null, null, 99, 1)
            .observeOn(schedulers.main())
            .subscribe(object : SingleObserver<List<Repository>> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: List<Repository>) {
                    onGetUserReposSuccess(t, userInfo)
                }

                override fun onError(e: Throwable?) {
                    onGetUserReposError(e)
                }
            })
        viewState.showCenterString(userInfo.htmlUrl)
        viewState.showBottomString("Заглушка нижней строки")
        viewState.hideProgressBar()
    }

    fun onGetUserByLoginError(e: Throwable?) {
        viewState.hideProgressBar()
        viewState.showErrorBar()
    }

    fun onGetUserReposSuccess(userRepositories: List<Repository>, userInfo: GithubUserAdvanced) {
        viewState.showTopString(
            "Загружено публичных репозиториев :"
                    + userRepositories.size + " из " + userInfo.publicRepos
        )
        reposListPresenter.repositories.addAll(userRepositories)
        reposListPresenter.itemClickListener = { itemView ->
            router.navigateTo(AndroidScreens().repoInfo(userRepositories[itemView.pos].url))
        }
        viewState.updateList()
    }

    fun onGetUserReposError(e: Throwable?) {
        viewState.showTopString("Ошибка при попытке чтения спискаа репозиториев")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }
}