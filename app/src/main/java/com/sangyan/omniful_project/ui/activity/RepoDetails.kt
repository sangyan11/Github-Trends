package com.sangyan.omniful_project.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sangyan.omniful_project.databinding.ActivityRepoDetailsBinding

class RepoDetails : AppCompatActivity() {
    private lateinit var binding : ActivityRepoDetailsBinding
    private lateinit var fullName : String
    private  var starCount  = 0
    private  var forkCount  = 0
    private lateinit var image : String
    private lateinit var description : String
    private lateinit var ownerName : String
    private lateinit var webUrl : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()
        initialViews()

    }

    private fun setViews() {
        fullName = intent.getStringExtra(MainActivity.FULL_NAME).toString()
        starCount = intent.getIntExtra(MainActivity.STARGAZERS_COUNT,0)
        forkCount = intent.getIntExtra(MainActivity.FORK_COUNT,0)
        image = intent.getStringExtra(MainActivity.IMG).toString()
        description = intent.getStringExtra(MainActivity.DESCRIPTION).toString()
        ownerName = intent.getStringExtra(MainActivity.OWNERNAME).toString()
        webUrl = intent.getStringExtra(MainActivity.Web_URL).toString()
    }

    private fun initialViews() {
        binding.tvName.text = fullName
        binding.starCount.text  = "$starCount Stars"
        binding.forkCount.text  =  "$forkCount Forks"
        binding.desc.text = description
        binding.tvName1.text = ownerName
        binding.webView.loadUrl(webUrl)
        Glide.with(applicationContext).load(image).into(binding.ownerAvatarImage)
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@RepoDetails,MainActivity::class.java))
        }
    }


}
