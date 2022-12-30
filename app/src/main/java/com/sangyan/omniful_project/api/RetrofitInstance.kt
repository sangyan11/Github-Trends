package com.sangyan.omniful_project.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : GithubApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java)
    }
}