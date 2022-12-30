package com.sangyan.omniful_project.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sangyan.omniful_project.adadper.GitHubAdapter
import com.sangyan.omniful_project.viewmodel.GitHubViewModel
import com.sangyan.omniful_project.databinding.ActivityMainBinding
import com.sangyan.omniful_project.model.Item

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var viewModel : GitHubViewModel
    private lateinit var gitHubAdapter: GitHubAdapter

    companion object {
        const val FULL_NAME = "FULLNAME"
        const val STARGAZERS_COUNT = "STARCOUNT"
        const val FORK_COUNT = "FORKCOUNT"
        const val IMG = "IMG"
        const val DESCRIPTION = "DESCRIPTION"
        const val OWNERNAME = "OWNERNAME"
        const val Web_URL = "WEBURL"

     }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[GitHubViewModel::class.java]
        viewModel.getAllKotlinRepos()
        viewModel.observeGitHubLiveData().observe(this, Observer { it->
            gitHubAdapter.setData(it)
        })
       gitHubAdapter.setOnGitHubRepoClickListener(object  :
           GitHubAdapter.SetOnGitHubRepoClickListener {
           override fun setOnClickListener(item: Item) {
               val intent = Intent(applicationContext, RepoDetails::class.java)
               intent.putExtra(FULL_NAME,item.name)
               intent.putExtra(STARGAZERS_COUNT,item.stargazers_count).toString()
               intent.putExtra(FORK_COUNT,item.forks_count).toString()
               intent.putExtra(IMG,item.owner.avatar_url)
               intent.putExtra(DESCRIPTION,item.description)
               intent.putExtra(OWNERNAME,item.owner.login)
               intent.putExtra(Web_URL,item.html_url)
               startActivity(intent)
           }

       })
    }

    private fun prepareRecyclerView() {
       gitHubAdapter = GitHubAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.adapter = gitHubAdapter
    }

}