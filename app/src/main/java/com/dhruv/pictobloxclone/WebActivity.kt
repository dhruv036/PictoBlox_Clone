package com.dhruv.pictobloxclone

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhruv.pictobloxclone.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

   lateinit var binding : ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE


        val url = intent.getStringExtra("url")

        binding.webview.loadUrl(url.toString())
        binding.webview.settings.javaScriptEnabled =true
    }
}