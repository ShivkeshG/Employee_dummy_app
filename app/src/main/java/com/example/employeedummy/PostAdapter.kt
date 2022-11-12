package com.example.employeedummy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(private val postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var onItemClick : ((Post) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.employee_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.id.text = post.id.toString()
        holder.title.text = post.title
        holder.body.text = post.body

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(post)
        }

    }

    override fun getItemCount(): Int = postList.size

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.tvPostId)
        val title: TextView = view.findViewById(R.id.tvPostTitle)
        val body: TextView = view.findViewById(R.id.tvPostBody)

    }

}