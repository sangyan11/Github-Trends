package com.sangyan.omniful_project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sangyan.omniful_project.api.RetrofitInstance
import com.sangyan.omniful_project.model.Item
import com.sangyan.omniful_project.model.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubViewModel : ViewModel() {
    private  var githubLiveData = MutableLiveData<List<Item>>()

    fun getAllKotlinRepos(){
        RetrofitInstance.api.getKotlinRepos("language:kotlin",">2022-12-19").enqueue(object : Callback<Repo>{
            override fun onResponse(call: Call<Repo>, response: Response<Repo>) {
                 if (response.body()!=null){
                 githubLiveData.value = response.body()!!.items
                 }
                else{
                    return
                 }
            }

            override fun onFailure(call: Call<Repo>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }

        })
    }
    fun observeGitHubLiveData() : LiveData<List<Item>>{
        return githubLiveData
    }
}