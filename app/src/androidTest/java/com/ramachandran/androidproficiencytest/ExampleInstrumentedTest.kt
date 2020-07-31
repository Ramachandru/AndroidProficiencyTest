package com.ramachandran.androidproficiencytest

import android.app.Activity
import android.app.PendingIntent.getActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ramachandran.androidproficiencytest.network.model.Row
import com.ramachandran.androidproficiencytest.ui.ContentAdapter
import com.ramachandran.androidproficiencytest.ui.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before



/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
  //  lateinit var activity: Activity

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.ramachandran.androidproficiencytest", appContext.packageName)
    }

    @Test
    fun dataPasser(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
       // activity =appContext as Activity
        val contentAdapter = ContentAdapter(appContext)
        val recyclclerView = (appContext as MainActivity).findViewById<RecyclerView>(R.id.recycler)
        val layoutManager = LinearLayoutManager(appContext)
        var rowsList = mutableListOf<Row>()
        rowsList.add(Row("test_title","test_description","img_url"))
        rowsList.add(Row("test_title_1","test_description_1","img_url_1"))
        contentAdapter.setList(rowsList)
        recyclclerView.layoutManager = layoutManager
        recyclclerView.adapter = contentAdapter
    }
}
