package com.example.employeedummy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


class MainActivity : AppCompatActivity() {

    private var postArray = ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.rvEmployee)
        recycler.adapter = PostAdapter(postArray)
            .also {
            it.onItemClick = {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("post", it)
                startActivity(intent)
            }
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        recycler.adapter

        loadData()

    }

    private fun loadData() {
        val queue = Volley.newRequestQueue(this@MainActivity)
        val url = "https://jsonplaceholder.typicode.com/posts"

        val jsonObjectRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val data = response.toString()
                val jArray = JSONArray(data)

                for (i in 0 until jArray.length()) {
                    val obj = jArray.getJSONObject(i)
                    val id = obj.getInt("id")
                    val title = obj.getString("title")
                    val body = obj.getString("body")
                    Log.d("shiv", obj.toString())
                    val post = Post(
                        id,
                        title,
                        body
                    )
                    postArray.add(post)
                }
//                employeeArray.clear()
//                val jsonArray = response.getJSONArray("employees")
////                val employeeArray = ArrayList<Employee>()
//                for (i in 0 until jsonArray.length()) {
//                    val employeeJsonObject = jsonArray.getJSONObject(i)
//                    val employee = Employee(
//                        employeeJsonObject.getString("name"),
//                        employeeJsonObject.getString("age").toInt(),
//                        employeeJsonObject.getString("salary").toInt()
//                    )
//                    employeeArray.add(employee)
//                }

            },
            { error ->
                error.printStackTrace()
            }
        )

        queue.add(jsonObjectRequest)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }
}