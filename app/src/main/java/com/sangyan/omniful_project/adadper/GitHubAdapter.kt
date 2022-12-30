package com.sangyan.omniful_project.adadper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sangyan.omniful_project.databinding.RecyclerLayoutBinding
import com.sangyan.omniful_project.model.Item

class GitHubAdapter() : RecyclerView.Adapter<GitHubAdapter.ViewHolder>() {

    var data = ArrayList<Item>()
    private lateinit var setOnGitHubRepoClickListener: SetOnGitHubRepoClickListener
    fun setOnGitHubRepoClickListener(setOnGitHubRepoClickListener: SetOnGitHubRepoClickListener) {
        this.setOnGitHubRepoClickListener = setOnGitHubRepoClickListener
    }
    fun setData(data : List<Item>){
        this.data = data as ArrayList<Item>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RecyclerLayoutBinding)  : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context) ,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.binding.repoName.text = data[position].name
        var max = 0
        if (data[position].stargazers_count> max){
            max = data[position].stargazers_count
            holder.binding.noOfStars.text = max.toString()
        }
        holder.binding.description.text = data[position].description
        holder.itemView.setOnClickListener{
          setOnGitHubRepoClickListener.setOnClickListener(data[position])
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface SetOnGitHubRepoClickListener{
           fun setOnClickListener(item : Item)
    }
}