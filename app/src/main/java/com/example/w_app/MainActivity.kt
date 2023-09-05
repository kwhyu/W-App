package com.example.w_app

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var Waipurv: RecyclerView
    private val list = ArrayList<Waipu>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        Waipurv = findViewById(R.id.waipu_rv)
        Waipurv.setHasFixedSize(true)

        list.addAll(getListWaipus())
        showRecyclerList()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_profile -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListWaipus(): ArrayList<Waipu> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataLikes = resources.getStringArray(R.array.data_aka)
        val listWaipu = ArrayList<Waipu>()
        for (i in dataName.indices) {
            val waipu = Waipu(dataName[i], dataDescription[i], dataPhoto[i], dataLikes[i])
            listWaipu.add(waipu)
        }
        return listWaipu
    }

    private fun showRecyclerList() {
        Waipurv.layoutManager = LinearLayoutManager(this)
        val listWaipuAdapter = ListWaipuAdapter(list)
        Waipurv.adapter = listWaipuAdapter

        listWaipuAdapter.setOnItemClickCallback(object : ListWaipuAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Waipu) {
                showSelectedWaipu(data)
            }
        })
    }

    private fun showSelectedWaipu(waipu: Waipu) {
        Toast.makeText(this, "Kamu memilih " + waipu.name, Toast.LENGTH_SHORT).show()
    }
}