package com.example.tugas2mcaviana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnjingAdapter (private val namaList : ArrayList<DataAnjing>): RecyclerView.Adapter<AnjingAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick (position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class MyViewHolder(itemData: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemData) {
        val gambar : ImageView = itemData.findViewById(R.id.id_Gambar)
        val nama : TextView = itemData.findViewById(R.id.id_Nama)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemData = LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return MyViewHolder(itemData, mListener)
    }

    override fun getItemCount(): Int {
        return namaList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = namaList[position]
        holder.gambar.setImageResource(currentItem.gambar)
        holder.nama.text = currentItem.nama
    }


}