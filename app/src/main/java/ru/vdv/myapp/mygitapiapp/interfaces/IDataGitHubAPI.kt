package ru.vdv.myapp.mygitapiapp.interfaces

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.vdv.myapp.mygitapiapp.model.GithubUser
import ru.vdv.myapp.mygitapiapp.model.GithubUserAdvanced

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
}