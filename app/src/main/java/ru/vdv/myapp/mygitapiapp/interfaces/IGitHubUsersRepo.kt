package ru.vdv.myapp.mygitapiapp.interfaces

import io.reactivex.rxjava3.core.Single
import ru.vdv.myapp.mygitapiapp.model.GithubUser
import ru.vdv.myapp.mygitapiapp.model.GithubUserAdvanced
import ru.vdv.myapp.mygitapiapp.model.Repository

/**
 * Интерфейс репозитория GitHub
 */

interface IGitHubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>

    fun getUserByLogin(login: String): Single<GithubUserAdvanced>

    fun getUserRepos(
        login: String,
        type: String?,
        sort: String?,
        direction: String?,
        perPage: Int?,
        page: Int?
    ): Single<List<Repository>>

    fun getRepositoryByUrl(url: String): Single<Repository>
}