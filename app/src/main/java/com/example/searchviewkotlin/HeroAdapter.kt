package com.example.searchviewkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 1. Membuat kelas HeroAdapter yang meng-extend RecyclerView.Adapter<HeroAdapter.LanguageViewHolder>.
class HeroAdapter(var mList: List<HeroData>) :
    RecyclerView.Adapter<HeroAdapter.LanguageViewHolder>() {

    // 2. Mendefinisikan antarmuka OnItemClickListener.
    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    // 3. Metode untuk mengatur listener OnItemClickListener.
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    // 4. Kelas inner LanguageViewHolder yang meng-extend RecyclerView.ViewHolder.
    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo : ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv : TextView = itemView.findViewById(R.id.titleTv)
    }

    // 5. Metode untuk mengubah daftar yang ditampilkan.
    fun setFilteredList(mList: List<HeroData>){
        this.mList = mList
        notifyDataSetChanged()
    }

    // 6. Metode onCreateViewHolder untuk membuat tampilan untuk setiap item dalam RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item , parent , false)
        return LanguageViewHolder(view)
    }

    // 7. Metode onBindViewHolder untuk mengikat data pada posisi tertentu ke tampilan ViewHolder.
    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.logo.setImageResource(mList[position].logo)
        holder.titleTv.text = mList[position].title
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    // 8. Metode getItemCount untuk mendapatkan jumlah item dalam daftar.
    override fun getItemCount(): Int {
        return mList.size
    }
}
