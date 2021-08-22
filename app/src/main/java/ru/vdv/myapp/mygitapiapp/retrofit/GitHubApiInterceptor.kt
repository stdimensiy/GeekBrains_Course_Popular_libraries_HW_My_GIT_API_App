package ru.vdv.myapp.mygitapiapp.retrofit

import okhttp3.Interceptor
import okhttp3.Response

object GitHubApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(
            chain.request()
                .newBuilder()
                .header("accept", "application/vnd.github.v3+json")
                .build()
        )
        return response
    }
}