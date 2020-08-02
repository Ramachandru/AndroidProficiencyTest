package com.ramachandran.androidproficiencytest.ui

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

@RunWith(JUnit4::class)
class MainActivityTest {

    @get:Rule
    public val activityRule :ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)
    private var activity : Activity ? =null
    @Before
    fun setUp() {
        activity = activityRule.activity
    }
    @Test
    fun dataPasser(){
        val context : Context = activity as Context
        val contentAdapter = ContentAdapter(context)
        val recyclclerView = (activity)!!.findViewById<RecyclerView>(R.id.recycler)
        val layoutManager = LinearLayoutManager(context)
        var rowsList = mutableListOf<Row>()
        rowsList.add(Row("test_title","test_description","img_url"))
        rowsList.add(Row("test_title_1","test_description_1","img_url_1"))
        contentAdapter.setList(rowsList)
        activity?.runOnUiThread {
            recyclclerView.layoutManager = layoutManager
            recyclclerView.adapter = contentAdapter
        }

    }
    @After
    fun tearDown() {
        activity= null
    }
}