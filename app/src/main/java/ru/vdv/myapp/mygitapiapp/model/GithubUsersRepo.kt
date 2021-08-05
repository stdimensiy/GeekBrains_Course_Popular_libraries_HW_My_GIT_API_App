package ru.vdv.myapp.mygitapiapp.model

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser(
            "login1", 1, "", "", "", "", "",
            "", "", "", "", "", "",
            "", "", "", "", false
        ),
        GithubUser(
            "login2", 2, "", "", "", "", "",
            "", "", "", "", "", "",
            "", "", "", "", false
        ),
        GithubUser(
            "login3", 3, "", "", "", "", "",
            "", "", "", "", "", "",
            "", "", "", "", false
        ),
        GithubUser(
            "login4", 4, "", "", "", "", "",
            "", "", "", "", "", "",
            "", "", "", "", false
        ),
        GithubUser(
            "login5", 5, "", "", "", "", "",
            "", "", "", "", "", "",
            "", "", "", "", false
        ),
    )

    fun getUsers(): List<GithubUser> {
        return repositories
    }

}