package com.egiwon.myrecyclerviewsample

import android.os.Bundle
import com.egiwon.myrecyclerviewsample.base.BaseActivity
import com.egiwon.myrecyclerviewsample.databinding.ActivityMainBinding
import com.egiwon.myrecyclerviewsample.ext.startActivity
import com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll.AutoHorizontalScrollActivity
import com.egiwon.myrecyclerviewsample.ui.circlehorizontalscroll.CircleHorizontalScrollActivity

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onClick()
    }

    private fun onClick() {
        binding.buttonAutoHorizontalScroll.setOnClickListener {
            startActivity<AutoHorizontalScrollActivity>()
        }

        binding.buttonCircleHorizontalScroll.setOnClickListener {
            startActivity<CircleHorizontalScrollActivity>()
        }
    }
}
