package ru.vdv.myapp.mygitapiapp.model

import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

/**
 * С внедрением RxJava данный класс теперь стал работать как Producer
 */

class GithubUsersRepo {
    private val userPlug = GithubUserAdvanced(
        "mojombo",
        1,
        "MDQ6VXNlcjE=",
        "https://avatars.githubusercontent.com/u/1?v=4",
        "",
        "https://api.github.com/users/mojombo",
        "https://github.com/mojombo",
        "https://api.github.com/users/mojombo/followers",
        "https://api.github.com/users/mojombo/following{/other_user}",
        "https://api.github.com/users/mojombo/gists{/gist_id}",
        "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
        "https://api.github.com/users/mojombo/subscriptions",
        "https://api.github.com/users/mojombo/orgs",
        "https://api.github.com/users/mojombo/repos",
        "https://api.github.com/users/mojombo/events{/privacy}",
        "https://api.github.com/users/mojombo/received_events",
        "User",
        false,
        "Nikolas Papadopolas",
        "Sam Po Sebe",
        "Https://souos.ru",
        "St-Petersburg",
        "StDimensiy@yandex.ru",
        true,
        "Nemnogo o sebe",
        "StDimensiy",
        32,
        2,
        20,
        5,
        "2020-10-26T19:49:06Z",
        "2021-08-11T20:44:38Z"
    )

    private val repositories = listOf(
        GithubUser(
            "mojombo",
            1,
            "MDQ6VXNlcjE=",
            "https://avatars.githubusercontent.com/u/1?v=4",
            "",
            "https://api.github.com/users/mojombo",
            "https://github.com/mojombo",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "defunkt",
            2,
            "MDQ6VXNlcjI=",
            "https://avatars.githubusercontent.com/u/2?v=4",
            "",
            "https://api.github.com/users/defunkt",
            "https://github.com/defunkt",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "pjhyett",
            3,
            "MDQ6VXNlcjM=",
            "https://avatars.githubusercontent.com/u/3?v=4",
            "",
            "https://api.github.com/users/pjhyett",
            "https://github.com/pjhyett",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "wycats",
            4,
            "MDQ6VXNlcjQ=",
            "https://avatars.githubusercontent.com/u/4?v=4",
            "",
            "https://api.github.com/users/wycats",
            "https://github.com/wycats",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "ezmobius",
            5,
            "MDQ6VXNlcjU=",
            "https://avatars.githubusercontent.com/u/5?v=4",
            "",
            "https://api.github.com/users/ezmobius",
            "https://github.com/ezmobius",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "ivey",
            6,
            "MDQ6VXNlcjY=",
            "https://avatars.githubusercontent.com/u/6?v=4",
            "",
            "https://api.github.com/users/ivey",
            "https://github.com/ivey",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "evanphx",
            7,
            "MDQ6VXNlcjc=",
            "https://avatars.githubusercontent.com/u/7?v=4",
            "",
            "https://api.github.com/users/evanphx",
            "https://github.com/evanphx",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "vanpelt",
            17,
            "MDQ6VXNlcjE3",
            "https://avatars.githubusercontent.com/u/17?v=4",
            "",
            "https://api.github.com/users/vanpelt",
            "https://github.com/vanpelt",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "wayneeseguin",
            18,
            "MDQ6VXNlcjE4",
            "https://avatars.githubusercontent.com/u/18?v=4",
            "",
            "https://api.github.com/users/wayneeseguin",
            "https://github.com/wayneeseguin",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "brynary",
            19,
            "MDQ6VXNlcjE5",
            "https://avatars.githubusercontent.com/u/19?v=4",
            "",
            "https://api.github.com/users/brynary",
            "https://github.com/brynary",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "kevinclark",
            20,
            "MDQ6VXNlcjIw",
            "https://avatars.githubusercontent.com/u/20?v=4",
            "",
            "https://api.github.com/users/kevinclark",
            "https://github.com/kevinclark",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "technoweenie",
            21,
            "MDQ6VXNlcjIx",
            "https://avatars.githubusercontent.com/u/21?v=4",
            "",
            "https://api.github.com/users/technoweenie",
            "https://github.com/technoweenie",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
        GithubUser(
            "macournoyer",
            22,
            "MDQ6VXNlcjIy",
            "https://avatars.githubusercontent.com/u/22?v=4",
            "",
            "https://api.github.com/users/macournoyer",
            "https://github.com/macournoyer",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/repos",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/received_events",
            "User",
            false
        ),
    )

    /**
     * Имитирует сетевой запрос поэтому (согласно рекомендаций)
     * применет тип Single который эмити Один объект или выдает ошибку.
     * Cогласно API GitHub разовая выдача коллекции пользователей содержит по умолчанию 30 элементов
     * поэтому передаем их какраз на разбор единой коллекцией
     *
     * .delay(5L, TimeUnit.SECONDS) имитирует задержку на выполнение сетевого запроса
     */
    fun getUsers(): Single<List<GithubUser>> {
        return Single.just(repositories).delay(5L, TimeUnit.SECONDS)
    }

    /**
     * Имитирует сетевой запрос поэтому (согласно рекомендаций)
     * применет тип Single который эмити Один объект или выдает ошибку.
     * Результат запроса - объект GithubUserAdvanced (с расширенным набором публичных полей)
     * дополнительно (сознательно) реализована выдача ошибки при обращении к пользователю с
     * идентификатром равным 3
     */
    fun getUserById(id: Int): Single<GithubUserAdvanced> {
        // для тестирования выдаю конкретный результат без привязки id (пока)
        val ss: Single<GithubUserAdvanced>
        if (id == 3) {
            ss = Single.error(Throwable("Запланированная ошибка"))
        } else {
            ss = Single.just(userPlug).delay(3L, TimeUnit.SECONDS)
        }
        return ss
    }
}