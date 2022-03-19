package com.egiwon.myrecyclerviewsample.ui.verticalperformance

import android.os.Bundle
import androidx.activity.viewModels
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.base.BaseActivity
import com.egiwon.myrecyclerviewsample.databinding.ActivityVerticalBinding
import com.egiwon.myrecyclerviewsample.ui.ImageViewModel
import com.egiwon.myrecyclerviewsample.ui.MultiViewTypeAdapter
import com.egiwon.myrecyclerviewsample.ui.ViewType
import com.egiwon.myrecyclerviewsample.ui.model.Photos
import com.egiwon.myrecyclerviewsample.ui.model.RecyclerItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerticalOrientationActivity: BaseActivity<ActivityVerticalBinding>(
    R.layout.activity_vertical
) {
    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvImages.adapter = MultiViewTypeAdapter()
        binding.rvImages.setHasFixedSize(true)

        viewModel.loadImageRecyclerItems(30)
        setObserve()
    }

    private fun setObserve() {
        viewModel.recyclerItems.observe(this) {
            (binding.rvImages.adapter as? MultiViewTypeAdapter)?.replaceItems(it)
        }
    }
}
