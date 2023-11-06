// 1. Package tempat kelas MainActivity berada.
package com.example.searchviewkotlin

// 2. Import berbagai komponen yang akan digunakan dalam kelas MainActivity.
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

// 3. MainActivity meng-extend kelas AppCompatActivity.
class MainActivity : AppCompatActivity() {

    // 4. Deklarasi variabel recyclerView, searchView, mList, dan adapter.
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<HeroData>()
    private lateinit var adapter: HeroAdapter

    // 5. Metode onCreate dipanggil ketika aktivitas dibuat.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 6. Inisialisasi recyclerView dan searchView.
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        // 7. Konfigurasi recyclerView.
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 8. Memasukkan data ke dalam mList dan membuat adapter.
        addDataToList()
        adapter = HeroAdapter(mList)

        // 9. Menetapkan adapter ke recyclerView.
        recyclerView.adapter = adapter

        // 10. Menetapkan listener untuk item yang diklik pada adapter.
        adapter.setOnItemClickListener(object : HeroAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val hero = mList[position]
                val heroName = hero.title
                Toast.makeText(this@MainActivity, "Nama pahlawan: $heroName", Toast.LENGTH_SHORT).show()
            }
        })

        // 11. Menetapkan listener untuk perubahan teks pada searchView.
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

    // 12. Metode untuk menyaring daftar pahlawan berdasarkan teks yang dimasukkan.
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

    // 13. Metode untuk menambahkan data pahlawan ke dalam mList.
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
