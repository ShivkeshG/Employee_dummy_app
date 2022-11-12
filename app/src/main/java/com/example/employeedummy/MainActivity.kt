package com.example.employeedummy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.rvEmployee)
        recycler.layoutManager = LinearLayoutManager(this)
        val items = loadData()
        val mAdapter = PostAdapter(items)
        recycler.adapter = mAdapter

        mAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("post", it)
            startActivity(intent)
        }
        recycler.setHasFixedSize(true)
        recycler.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

    }

    private fun loadData(): ArrayList<Post> {
        val queue = Volley.newRequestQueue(this@MainActivity)
        val url = "https://jsonplaceholder.typicode.com/posts"

        val list = ArrayList<Post>()

        val jsonObjectRequest = StringRequest(Request.Method.GET, url,
            { response ->
                list.clear()
                val data = response.toString()
                val jArray = JSONArray(data)

                for (i in 0 until jArray.length()) {
                    val obj = jArray.getJSONObject(i)
                    val id = obj.getInt("id")
                    val title = obj.getString("title")
                    val body = obj.getString("body")
//                    Log.d("shiv", obj.toString())
                    val post = Post(
                        id,
                        title,
                        body
                    )
                    list.add(post)
                }

            },
            { error ->
                error.printStackTrace()
            }
        )

        queue.add(jsonObjectRequest)

        return list
    }

}