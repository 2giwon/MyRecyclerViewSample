package com.egiwon.myrecyclerviewsample.ui.verticalperformance

import android.os.Bundle
import androidx.activity.viewModels
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.base.BaseActivity
import com.egiwon.myrecyclerviewsample.databinding.ActivityVerticalBinding
import com.egiwon.myrecyclerviewsample.ui.ImageViewModel
import com.egiwon.myrecyclerviewsample.ui.MultiViewTypeAdapter

class VerticalOrientationActivity: BaseActivity<ActivityVerticalBinding>(
    R.layout.activity_vertical
) {
    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvImages.adapter = MultiViewTypeAdapter()
        binding.rvImages.setHasFixedSize(true)
    }
}
