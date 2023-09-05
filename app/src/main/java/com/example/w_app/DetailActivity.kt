package com.example.w_app

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.w_app.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataWaipu = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Waipu>("key_waipu", Waipu::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Waipu>("key_waipu")
        }
        Glide.with(this)
            .load(dataWaipu?.photo)
            .into(binding.imageView)
        binding.textView.text=dataWaipu?.name
        binding.textdesc2.text=dataWaipu?.description
        binding.textlike2.text=dataWaipu?.likes

        binding.imageButton3.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Looks My Waipu <3 ${dataWaipu?.name} \n" + "${dataWaipu?.photo}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}