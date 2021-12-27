package com.egiwon.myrecyclerviewsample.ui.circlehorizontalscroll

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.base.BaseActivity
import com.egiwon.myrecyclerviewsample.databinding.ActivityCircleScrollBinding
import com.egiwon.myrecyclerviewsample.ui.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CircleHorizontalScrollActivity : BaseActivity<ActivityCircleScrollBinding>(
    R.layout.activity_circle_scroll
) {

    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvCircleScroll.adapter = CircleHorizontalScrollAdapter()
        binding.rvCircleScroll.setHasFixedSize(true)
        val helper = PagerSnapHelper()
        helper.attachToRecyclerView(binding.rvCircleScroll)

        viewModel.loadRandomImages(10)
        setObserve()
    }

    private fun setObserve() {
        viewModel.photos.observe(this, {
            (binding.rvCircleScroll.adapter as? CircleHorizontalScrollAdapter)?.replaceItems(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}
