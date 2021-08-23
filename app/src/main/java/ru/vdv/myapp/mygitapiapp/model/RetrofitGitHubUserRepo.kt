package ru.vdv.myapp.mygitapiapp.model

import io.reactivex.rxjava3.core.Single
import ru.vdv.myapp.mygitapiapp.interfaces.IDataGitHubAPI
import ru.vdv.myapp.mygitapiapp.interfaces.IGitHubUsersRepo
import ru.vdv.myapp.mygitapiapp.myschedulers.MySchedulersFactory

class RetrofitGitHubUserRepo(val api: IDataGitHubAPI) : IGitHubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> {
        return api.getUsers().subscribeOn(MySchedulersFactory.create().io())
    }

    override fun getUserByLogin(login: String): Single<GithubUserAdvanced> {
        return api.getUserByLogin(login).subscribeOn(MySchedulersFactory.create().io())
    }

    override fun getUserRepos(
        login: String,
        type: String?,
        sort: String?,
        direction: String?,
        perPage: Int?,
        page: Int?
    ): Single<List<Repository>> {
        return api.getUserRepos(login, type, sort, direction, perPage, page)
            .subscribeOn(MySchedulersFactory.create().io())
    }

    override fun getRepositoryByUrl(url: String): Single<Repository> {
        return api.getRepositoryByUrl(url).subscribeOn(MySchedulersFactory.create().io())
    }
}