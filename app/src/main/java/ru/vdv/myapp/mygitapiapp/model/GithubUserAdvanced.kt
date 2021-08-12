package ru.vdv.myapp.mygitapiapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Класс GithubUserAdvanced (создан для работы с API Github)
 * отвечает за хранение публичной расширенной информации о пользователе
 *
 * @param login ............. - логин пользователя (ник)
 * @param id ................ - уникальный идентификатор пользователя
 * @param nodeId ............ - хэш идентификатор
 * @param avatarUrl ......... - ссылка на базовый аватар пользователя
 * @param gravatarId ........ - пока не понял, нне попадался еще заполненный параметра (позже)
 * @param url ............... - адрес запроса API github для получения расширенной информации о пользователе
 * @param htmlUrl ........... - адрес для перехода на страничку пользователя на github
 * @param followersUrl ...... - адрес запроса API github для получения выборки фолловеров (пользователей подписчиков) пользователя
 * @param followingUrl ...... - адрес запроса API github для получения выборки пользователей на которых подписан пользователь
 * @param gistsUrl .......... - адрес запроса API github (пока не определил точно что он делает)
 * @param starredUrl ........ - адрес запроса API github (пока не определил точно что он делает)
 * @param subscriptionsUrl .. - адрес запроса API github (пока не определил точно что он делает)
 * @param organizationsUrl .. - адрес запроса API github (пока не определил точно что он делает)
 * @param reposUrl .......... - адрес запроса API github для получения выборки репозиториев пользователя
 * @param eventsUrl ......... - адрес запроса API github (пока не определил точно что он делает)
 * @param receivedEventsUrl . - адрес запроса API github (пока не определил точно что он делает)
 * @param type .............. - тип пользователя
 * @param siteAdmin ......... - признак того, что пользователь является администратором
 * @param name .............. - Имя пользователя (или ник)
 * @param company ........... - Наименование компании (фирмы) где работает пользователь
 * @param blog .............. - Адрес личного блога (странички) пользователя
 * @param location .......... - Адррес (город, страна) пользователя
 * @param email ............. - Адрес электронной почты пользователя
 * @param hireable .......... - Статус пользователя (нанимаемый / не нанимаемый) (я так перевел)
 * @param bio ............... - Информация о пользователе
 * @param twitterUsername ... - Ник пользователя в twitter
 * @param publicRepos ....... - Количество публичных репозиториев
 * @param publicGists ....... - Количество публичных фрагментов кода
 * @param followers ......... - Количество подписчиков у пользователя
 * @param following ......... - Количество акаунтов на которые подписан пользователь
 * @param createdAt ......... - Дата создания акаунта
 * @param updatedAt ......... - Дата последнего обновления акаунта
 *
 * @constructor создает объект, содержащий краткую общую информацию о пользователе
 */

@Parcelize
data class GithubUserAdvanced(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("followers_url")
    val followersUrl: String,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("gists_url")
    val gistsUrl: String,
    @SerializedName("starred_url")
    val starredUrl: String,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,
    @SerializedName("organizations_url")
    val organizationsUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("events_url")
    val eventsUrl: String,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean = false,
    @SerializedName("name")
    val name: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("blog")
    val blog: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("hireable")
    val hireable: Boolean,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("public_gists")
    val publicGists: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,

    ) : Parcelable
