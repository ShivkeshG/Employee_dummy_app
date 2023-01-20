package com.example.employeedummy

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.employeedummy.models.PostListItem

class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val post: PostListItem? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("post", PostListItem::class.java)
        }else{
            intent.getParcelableExtra("post")
        }

        if (post != null) {
            val id: TextView = findViewById(R.id.tvDetailId)
            val title: TextView = findViewById(R.id.tvDetailTitle)
            val body: TextView = findViewById(R.id.tvDetailBody)

            id.text = post.id.toString()
            title.text = post.title
            body.text = post.body
        }
    }

}