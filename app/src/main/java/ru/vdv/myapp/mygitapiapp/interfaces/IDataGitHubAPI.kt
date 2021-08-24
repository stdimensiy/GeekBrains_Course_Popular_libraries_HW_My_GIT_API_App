package ru.vdv.myapp.mygitapiapp.interfaces

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import ru.vdv.myapp.mygitapiapp.model.GithubUser
import ru.vdv.myapp.mygitapiapp.model.GithubUserAdvanced
import ru.vdv.myapp.mygitapiapp.model.Repository

interface IDataGitHubAPI {
    /**
     * Режим: List users - получение списка пользователей
     * @param since ........ - (Int) Идентификатор пользователя. Отобрадение списка пользователй со
     * значением идентификатора больше указанного в параметре. Именно при помощи этого параметра
     * происходит разделение общего списка пользователей на страницы.
     * @param perPage ..... - (Int) количество элементов списка ответа (1...100) (по умолчани = 30)
     * @return возвращает список пользователей в порядке их регистрации на GitHub...
     */
    @GET("/users")
    fun getUsers(
        @Query("since") since: Int? = null,
        @Query("per_page") perPage: Int? = null
    ): Single<List<GithubUser>>

    /**
     * Режим: Get a user - получение общедоступной информации о пользователе с учетной записью GitHub
     * @param login .... - (String) уникальный логин пользователя на GitHub. Всегда в нижнем регистре.
     * @return возвращает расширенный объект информации о пользователе (больше чем при выдаче
     * пользователей списком)...
     */
    @GET("/users/{username}")
    fun getUserByLogin(
        @Path("username") login: String
    ): Single<GithubUserAdvanced>

    /**
     * Режим: List repositories for a user - получение списка общедоступных репозиториев для
     * указанного пользователя с учетной записью GitHub
     * @param login ..... - (String) уникальный логин пользователя на GitHub. Всегда в нижнем регистре.
     * @param type ...... - (String) тип репозитория где указанный пользователь либо владелец либо
     *                              участник, по умолчанию участник (all / owner / member)
     * @param sort ...... - (String) Тип сортировки результатов, может быть одним: по созданию,
     * обновлению, отправке, по полному имени. По умолчанию:По полному имени
     * @param direction . - (String) Порядок сортировки, может быть либо asc либо desc.
     * По умолчанию: asc при использовании full_name, в противном случае desc
     * @param perPage ... - (Int) количество элементов списка ответа (1...100) (по умолчани = 30)
     * @param page ...... - (Int) номер страницы результатоы ответа . По умолчанию: 1
     * (created / updated / pushed / full_name)    Default: full_name
     * @return возвращает список репозиториев (объектов Repository) указанного пользователя
     * отвечающих заданным критериям...
     */
    @GET("/users/{username}/repos")
    fun getUserRepos(
        @Path("username") login: String,
        @Query("type") type: String?,
        @Query("sort") sort: String?,
        @Query("direction") direction: String?,
        @Query("per_page") perPage: Int?,
        @Query("page") page: Int?
    ): Single<List<Repository>>

    /**
     * Режим: Get a repository - получение конкретного общедоступного репозитория по указанному Url
     * @param url ..... - (String) уникальный логин пользователя на GitHub. Всегда в нижнем регистре.
     * @return возвращает репозиторий (объект Repository) по указанному URL
     */
    @GET
    fun getRepositoryByUrl(
        @Url url: String
    ): Single<Repository>


}