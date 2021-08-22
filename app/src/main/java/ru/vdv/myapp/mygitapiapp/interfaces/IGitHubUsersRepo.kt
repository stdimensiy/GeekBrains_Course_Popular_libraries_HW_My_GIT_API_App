package ru.vdv.myapp.mygitapiapp.interfaces

import io.reactivex.rxjava3.core.Single
import ru.vdv.myapp.mygitapiapp.model.GithubUser
import ru.vdv.myapp.mygitapiapp.model.GithubUserAdvanced

/**
 * Интерфейс репозитория GitHub
 */

interface IGitHubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserByLogin(login: String): Single<GithubUserAdvanced>
}