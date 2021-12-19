package com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll

import android.os.Bundle
import androidx.activity.viewModels
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

        viewModel.loadRandomImages(10)
        setObserve()
    }

    private fun setObserve() {

    }
}
