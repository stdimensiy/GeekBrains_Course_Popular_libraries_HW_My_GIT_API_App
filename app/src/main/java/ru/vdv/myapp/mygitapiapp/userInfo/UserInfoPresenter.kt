package ru.vdv.myapp.mygitapiapp.userInfo

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.vdv.myapp.mygitapiapp.AndroidScreens
import ru.vdv.myapp.mygitapiapp.interfaces.IReposListPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.RepoItemView
import ru.vdv.myapp.mygitapiapp.interfaces.UserInfoView
import ru.vdv.myapp.mygitapiapp.model.GithubUserAdvanced
import ru.vdv.myapp.mygitapiapp.model.Repository
import ru.vdv.myapp.mygitapiapp.model.RetrofitGitHubUserRepo

class UserInfoPresenter(
    val userLogin: String? = null,
    private val githubUsersRepo: RetrofitGitHubUserRepo,
    val router: Router
) : MvpPresenter<UserInfoView>() {
    val disposables = CompositeDisposable()

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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<GithubUserAdvanced> {
                    override fun onSubscribe(d: Disposable?) {
                        disposables.add(d)
                    }

                    override fun onSuccess(iserInfo: GithubUserAdvanced?) {
                        if (iserInfo != null) {
                            iserInfo.let {
                                viewState.showLogin(it.login)
                                viewState.setImageAvatar(it.avatarUrl)
                                viewState.showTopString("Заглушка верхей строки")
                                // Запрос состояния репозитория
                                githubUsersRepo
                                    .getUserRepos(userLogin, null, null, null, 99, 1)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(object : SingleObserver<List<Repository>> {
                                        override fun onSubscribe(d: Disposable?) {
                                            disposables.add(d)
                                        }

                                        override fun onSuccess(t: List<Repository>) {
                                            viewState.showTopString(
                                                "Загружено публичных репозиториев :"
                                                        + t.size + " из " + iserInfo.publicRepos
                                            )
                                            reposListPresenter.repositories.addAll(t)
                                            reposListPresenter.itemClickListener = { itemView ->
                                                router.navigateTo(AndroidScreens().repoInfo(t[itemView.pos].url))
                                            }
                                            viewState.updateList()
                                        }

                                        override fun onError(e: Throwable?) {
                                            viewState.showTopString("Ошибка при попытке чтения спискаа репозиториев")
                                        }
                                    })
                                viewState.showCenterString(it.htmlUrl)
                                viewState.showBottomString("Заглушка нижней строки")
                                viewState.hideProgressBar()
                            }
                        }
                    }

                    override fun onError(e: Throwable?) {
                        viewState.hideProgressBar()
                        viewState.showErrorBar()
                    }
                })
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }
}