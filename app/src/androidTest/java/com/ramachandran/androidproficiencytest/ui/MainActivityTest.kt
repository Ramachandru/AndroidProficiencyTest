package com.ramachandran.androidproficiencytest.ui

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.ramachandran.androidproficiencytest.R
import com.ramachandran.androidproficiencytest.network.model.Row
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    public val activityRule :ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)
    private var activity : Activity ? =null
    var contentAdapter : ContentAdapter? =null
    var layoutManager : LinearLayoutManager? =null
    var recyclclerView : RecyclerView ? =null
    @Before
    fun setUp() {
        activity = activityRule.activity
        val context : Context = activity as Context
        contentAdapter = ContentAdapter(context)
        recyclclerView = (activity)!!.findViewById<RecyclerView>(R.id.recycler)
        layoutManager = LinearLayoutManager(context)
        var rowsList = mutableListOf<Row>()
        rowsList.add(Row("test_title","test_description","img_url"))
        rowsList.add(Row("test_title_1","test_description_1","img_url_1"))
        contentAdapter?.setList(rowsList)

    }
    @Test
    fun swipeRefresh(){
        val swipeRefreshLayout = activity!!.findViewById<SwipeRefreshLayout>(R.id.swiperefresh)
        swipeRefreshLayout.setOnRefreshListener {
            activity?.runOnUiThread {
                contentAdapter!!.notifyDataSetChanged()
            }
        }
    }
    @Test
    fun dataPasser(){
        activity?.runOnUiThread {
            recyclclerView?.layoutManager = layoutManager
            recyclclerView?.adapter = contentAdapter
        }

    }
    @After
    fun tearDown() {
        activity= null
    }
}