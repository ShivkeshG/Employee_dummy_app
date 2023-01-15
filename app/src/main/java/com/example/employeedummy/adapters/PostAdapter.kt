package com.example.employeedummy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedummy.databinding.EmployeeItemLayoutBinding
import com.example.employeedummy.models.PostListItem

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var onItemClick : ((PostListItem) -> Unit)? = null

    inner class PostViewHolder(val binding: EmployeeItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<PostListItem>() {
        override fun areItemsTheSame(oldItem: PostListItem, newItem: PostListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostListItem, newItem: PostListItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            EmployeeItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = differ.currentList[position]
        holder.binding.apply {
            tvPostId.text = post.id.toString()
            tvPostTitle.text = post.title
            tvPostBody.text = post.body
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(post)
        }

    }

    override fun getItemCount(): Int = differ.currentList.size

}