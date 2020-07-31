package com.ramachandran.androidproficiencytest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ramachandran.androidproficiencytest.R
import com.ramachandran.androidproficiencytest.viewmodel.ContentViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var contentViewModel: ContentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val swipeRecyclerView = findViewById<SwipeRefreshLayout>(R.id.swiperefresh)
        contentViewModel=ViewModelProviders.of(this).get(ContentViewModel::class.java)
        val contentAdapter = ContentAdapter(this@MainActivity)
        val recyclclerView = findViewById<RecyclerView>(R.id.recycler)
        val layoutManager = LinearLayoutManager(this@MainActivity)
        contentAdapter.setList(mutableListOf())
        recyclclerView.layoutManager = layoutManager
        recyclclerView.adapter = contentAdapter
        contentViewModel.getCoreData().observe(this, Observer { content->
            supportActionBar!!.title = content.title
            contentAdapter.setList(content.rows)
            contentAdapter.notifyDataSetChanged()
        })
        swipeRecyclerView.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
          //  contentAdapter.clear()
            contentViewModel.getCoreData().observe(this, Observer { content->
                supportActionBar!!.title = content.title
                contentAdapter.setList(content.rows)
                contentAdapter.notifyDataSetChanged()
            })
            swipeRecyclerView.isRefreshing=false
            Toast.makeText(this@MainActivity,"Refreshed",Toast.LENGTH_SHORT).show()
        })
    }
}
