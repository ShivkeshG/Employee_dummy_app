package com.example.employeedummy

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val post = intent.getParcelableExtra<Post>("post")
        if (post != null) {
            val id: TextView = findViewById(R.id.tvDetailId)
            val title: TextView = findViewById(R.id.tvDetailTitle)
            val body: TextView = findViewById(R.id.tvDetailBody)

            id.text = "Id: " + post.id.toString()
            title.text = "Title: " + post.title.toString()
            body.text = "Body: " + post.body.toString()
        }
    }
}