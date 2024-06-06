package com.example.searchview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LangugaeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = LangugaeAdapter(mList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<LanguageData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"no data found",Toast.LENGTH_SHORT).show()
        }
        else{
            adapter.setFilteredList(filteredList)
        }
    }
}

    private fun addDataToList() {
        mList.add(LanguageData("Java",R.drawable.java))
        mList.add(LanguageData("c++",R.drawable.cplusplus))
        mList.add(LanguageData("csharp",R.drawable.csharp))
        mList.add(LanguageData("html",R.drawable.html))
        mList.add(LanguageData("javascript",R.drawable.javascript))
        mList.add(LanguageData("kotlin",R.drawable.kotlin))
        mList.add(LanguageData("python",R.drawable.python))
        mList.add(LanguageData("swift",R.drawable.swift))



    }
}