package com.example.tugas2mcaviana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    private lateinit var AnjingRecyclerView : RecyclerView
    private lateinit var listAnjing: ArrayList<DataAnjing>

    private lateinit var gambar: Array<Int>
    private lateinit var nama: Array<String>
    private lateinit var deskripsi: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // function untuk pindah ke halaman Profile
        getProfile()

        // function untuk Logout dan kembali ke halaman Login
        getLogout()

        // Area data recyclerview
        gambar = arrayOf(
            R.drawable.alaskan,
            R.drawable.corgi,
            R.drawable.shitzu,
            R.drawable.chihuahua,
            R.drawable.daschund,
            R.drawable.maltese,
            R.drawable.pomerian,
            R.drawable.poodle,
            R.drawable.pug,
            R.drawable.beagle,
            R.drawable.doberman_pinscher,
            R.drawable.golden,
            R.drawable.husky,
            R.drawable.labrador,
            R.drawable.leonberger,
            R.drawable.newfoundland,
            R.drawable.rottweiler,
            R.drawable.shitzu,
            R.drawable.jack
        )

        nama = arrayOf(
            "Alascan", "Corgi","Shitzu", "Chihuahua",
            "Dachshund", "Maltese","Pomeranian", "Poodle",
            "Pug", "Beagle", "Doberman_Pinscher", "Golden",
            "Husky", "Labrador", "Leonberger", "Newfoundland",
            "Rottweiler", "Shitzu", "Jack"
        )


        deskripsi = arrayOf(
            getString(R.string.alascan),
            getString(R.string.Corgi),
            getString(R.string.Shitzu),
            getString(R.string.Chihuahua),
            getString(R.string.Dachshund),
            getString(R.string.Maltese),
            getString(R.string.Pomeranian),
            getString(R.string.Poodle),
            getString(R.string.Pug),
            getString(R.string.beagle),
            getString(R.string.doberman_pinscher),
            getString(R.string.golden),
            getString(R.string.husky),
            getString(R.string.labardo),
            getString(R.string.leonberger),
            getString(R.string.newfoundland),
            getString(R.string.rottweiler),
            getString(R.string.shitzu),
            getString(R.string.Jack)
        )


        AnjingRecyclerView = findViewById(R.id.jenisAnjingRV)
        AnjingRecyclerView.layoutManager = LinearLayoutManager(this)
        AnjingRecyclerView.setHasFixedSize(true)

        listAnjing = arrayListOf<DataAnjing>()
        getDataAnjing()


    }

    private fun getProfile() {
        val btnProfil:Button = findViewById(R.id.btnProfile)

        btnProfil.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getLogout() {
        val btnLogout:Button = findViewById(R.id.btnLogout)

        btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getDataAnjing() {
        val list_anjing = ArrayList<DataAnjing>()
        for ( i in gambar.indices) {
            val data_anjing = DataAnjing(gambar[i],nama[i])
            list_anjing.add(data_anjing)
        }

        val adapter = AnjingAdapter(list_anjing)
        AnjingRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(object: AnjingAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("idgambar", list_anjing[position].gambar)
                intent.putExtra("idnama", list_anjing[position].nama)
                intent.putExtra("iddeskripsi", deskripsi[position])

                startActivity(intent)
            }
        })
    }



}