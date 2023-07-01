package com.example.listedassignment

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listedassignment.Adapters.LinksAdapter
import com.example.listedassignment.Models.OverallUrlChart
import com.example.listedassignment.ViewModel.LinksViewModel
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor
    private lateinit var showLocalTime : TextView
    private lateinit var linksViewModel: LinksViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var linksAdapter: LinksAdapter
    private lateinit var topLinks : Button
    var activeTab = "top"
    private lateinit var recentLinks : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialise()


        observeData()

        topLinks.setOnClickListener {
            if(activeTab == "top")
                return@setOnClickListener

            recentLinks.background = null
            topLinks.background = AppCompatResources.getDrawable(this,R.drawable.tab_selected)
            linksViewModel.linksData.observe(this) {
                linksAdapter.setModel(it.data.top_links)
            }
            activeTab = "top"
            topLinks.setTextColor(resources.getColor(R.color.white))
            recentLinks.setTextColor(resources.getColor(R.color.text_color_fade))
        }

        recentLinks.setOnClickListener {

            if(activeTab == "recent")
                return@setOnClickListener

            topLinks.background = null
            recentLinks.background = AppCompatResources.getDrawable(this,R.drawable.tab_selected)

            linksViewModel.linksData.observe(this) {
                linksAdapter.setModel(it.data.recent_links)
            }
            activeTab = "recent"
            topLinks.setTextColor(resources.getColor(R.color.text_color_fade))
            recentLinks.setTextColor(resources.getColor(R.color.white))
        }
    }

    private fun observeData() {
        Toast.makeText(this, "Fetching Data...", Toast.LENGTH_SHORT).show()
        linksViewModel.linksData.observe(this) {
            linksAdapter.setModel(it.data.top_links)
            fillLineChart(it.data.overall_url_chart)
            Log.d("Hello",it.data.toString())
        }

        linksViewModel.getLinksData(sharedPreferences.getString("token","").toString())
    }

    private fun fillLineChart(overallUrlChart: OverallUrlChart) {

    }

    private fun initialise() {
        showLocalTime = findViewById(R.id.mainActivityTextViewFirst)
        val timeRightNow = Calendar.getInstance()

        updateGreetingAccordingToTime(timeRightNow)

        sharedPreferences = getSharedPreferences("TokenInfo", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        recyclerView = findViewById(R.id.mainActRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        linksAdapter = LinksAdapter()
        recyclerView.adapter = linksAdapter
        linksViewModel = ViewModelProvider(this)[LinksViewModel::class.java]
        topLinks = findViewById(R.id.topLinks)
        recentLinks = findViewById(R.id.recentLinks)
        editor.putString("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI").apply()
    }

    private fun updateGreetingAccordingToTime(timeRightNow: Calendar) {
        val currentHour = timeRightNow[Calendar.HOUR_OF_DAY]

        if(currentHour in 6..11)
            showLocalTime.text = "Good Morning"
        else if(currentHour in 12 .. 15)
            showLocalTime.text = "Good Afternoon"
        else
            showLocalTime.text = "Good Evening"
    }
}