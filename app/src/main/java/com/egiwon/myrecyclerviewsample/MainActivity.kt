package com.egiwon.myrecyclerviewsample

import android.os.Bundle
import com.egiwon.myrecyclerviewsample.base.BaseActivity
import com.egiwon.myrecyclerviewsample.databinding.ActivityMainBinding
import com.egiwon.myrecyclerviewsample.ext.startActivity
import com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll.AutoHorizontalScrollActivity
import com.egiwon.myrecyclerviewsample.ui.circlehorizontalscroll.CircleHorizontalScrollActivity
import com.egiwon.myrecyclerviewsample.ui.customscrollbar.CustomScrollBarActivity
import com.egiwon.myrecyclerviewsample.ui.diffutil.DiffUtilActivity
import com.egiwon.myrecyclerviewsample.ui.verticalperformance.VerticalOrientationActivity
import com.egiwon.myrecyclerviewsample.ui.verticalperformance.VerticalOrientationHighActivity

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

        binding.buttonCustomScroll.setOnClickListener {
            startActivity<CustomScrollBarActivity>()
        }

        binding.buttonDiffutil.setOnClickListener {
            startActivity<DiffUtilActivity>()
        }

        binding.buttonVertical.setOnClickListener {
            startActivity<VerticalOrientationActivity>()
        }

        binding.buttonVertical2.setOnClickListener {
            startActivity<VerticalOrientationHighActivity>()
        }
    }
}
