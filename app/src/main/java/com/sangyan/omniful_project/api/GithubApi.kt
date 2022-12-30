package com.sangyan.omniful_project.api

import com.sangyan.omniful_project.model.Item
import com.sangyan.omniful_project.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("repositories?")
    fun getKotlinRepos(@Query("q") q : String, @Query("pushed") date: String) : Call<Repo>

    @GET("repositories?")
    fun getMoreResponse()  : Call<Item>
}