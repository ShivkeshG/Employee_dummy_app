package com.example.employeedummy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeedummy.adapters.PostAdapter
import com.example.employeedummy.api.RetrofitInstance.Companion.api
import com.example.employeedummy.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        lifecycleScope.launch {
            val postData = api.getAllPosts().body()
            postAdapter.differ.submitList(postData)
        }

        postAdapter.onItemClick = { post ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("post", post)
            startActivity(intent)
        }
        showPosts()
    }

    private fun showPosts() {
        lifecycleScope.launch(Dispatchers.IO) {
            val result = api.getAllPosts().body()
            Log.e("Check if retrofit works", result.toString())
        }
    }

    private fun setUpRecyclerView() = binding.rvEmployee.apply {
        postAdapter = PostAdapter()
        adapter = postAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
        addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
    }

}