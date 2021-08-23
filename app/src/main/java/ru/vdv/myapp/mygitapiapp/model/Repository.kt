package ru.vdv.myapp.mygitapiapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Класс (создан для работы с API Github)
 * отвечает за хранение публичной информации о репозитории
 *            (73 поля, для выполнения ДЗ приняты только часть)
 * @param id ................ - уникальный идентификатор репозитория
 * @param nodeId ............ - хэш идентификатор
 * @param name .............. - наименование репозитория (краткое)
 * @param fullName .......... - наименование репозитория (полное)
 * @param owner ............. - владелец репозитория (GithubUser -  именно это класс, сокращенный)
 * @param private ........... - признак приватный (true) или публичный (false) репозиторий
 * @param htmlUrl ........... - адрес для перехода на страничку репозитория на github
 * @param description ....... - описание репозитория на github
 * .....
 * @param forksCount ........ - текущее количество форков
 * @param stargazersCount ... - текущее количество звездочек
 * @param watchersCount ..... - текущее количество наблюдателей*
 * .....
 * @constructor создает объект, содержащий публичную информацию о публичном же репозитории
 */

@Parcelize
data class Repository(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String = "",
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("owner")
    val owner: GithubUser,
    @SerializedName("private")
    val private: Boolean = false,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("starred_url")
    val watchersCount: Int
) : Parcelable
