package com.egiwon.myrecyclerviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.egiwon.myrecyclerviewsample.databinding.ActivityMainBinding
import com.egiwon.myrecyclerviewsample.ext.startActivity
import com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll.AutoHorizontalScrollActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        onClick()
    }

    private fun onClick() {
        binding.buttonAutoHorizontalScroll.setOnClickListener {
            startActivity<AutoHorizontalScrollActivity>()
        }
    }
}
