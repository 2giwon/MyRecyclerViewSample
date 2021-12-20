package com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.base.BaseActivity
import com.egiwon.myrecyclerviewsample.databinding.ActivityAutoScrollBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AutoHorizontalScrollActivity : BaseActivity<ActivityAutoScrollBinding>(
    R.layout.activity_auto_scroll
) {

    private val viewModel: AutoScrollViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvImages.adapter = AutoHorizontalScrollAdapter(
            R.layout.item_auto_scroll
        )

        binding.rvImages.setHasFixedSize(true)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvImages)

        viewModel.loadRandomImages(10)
        setObserve()
    }

    private fun setObserve() {
        viewModel.photos.observe(this, {
            (binding.rvImages.adapter as? AutoHorizontalScrollAdapter)?.replaceItems(it)

            binding.rvImages.autoScroll(it.size)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onDestroy() {
        binding.rvImages.stopAutoScroll()
        super.onDestroy()
    }
}
