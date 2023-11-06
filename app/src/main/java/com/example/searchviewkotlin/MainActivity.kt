package com.example.searchviewkotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<HeroData>()
    private lateinit var adapter: HeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = HeroAdapter(mList)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : HeroAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val hero = mList[position]
                val heroName = hero.title
                Toast.makeText(this@MainActivity, "Nama pahlawan: $heroName", Toast.LENGTH_SHORT).show()
            }
        })

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
            val filteredList = ArrayList<HeroData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(HeroData("Ki Hajar Dewantara", R.drawable.kihajar))
        mList.add(HeroData("IR. Soekarno", R.drawable.presidensoekarno))
        mList.add(HeroData("Jenderal Soedirman", R.drawable.sudirman))
        mList.add(HeroData("R.A. Kartini", R.drawable.kartini))
        mList.add(HeroData("Pattimura", R.drawable.pattimura))
        mList.add(HeroData("Cut Nyak Dien", R.drawable.cutnyak))
        mList.add(HeroData("Supriyadi", R.drawable.supriadi))
    }
}