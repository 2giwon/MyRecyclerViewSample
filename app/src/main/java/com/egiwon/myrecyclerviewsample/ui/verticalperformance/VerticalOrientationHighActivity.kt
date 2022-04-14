package com.egiwon.myrecyclerviewsample.ui.verticalperformance

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.base.BaseActivity
import com.egiwon.myrecyclerviewsample.databinding.ActivityHighVerticalBinding
import com.egiwon.myrecyclerviewsample.ui.ImageViewModel
import com.egiwon.myrecyclerviewsample.ui.multiviewtype.MultiViewTypeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerticalOrientationHighActivity: BaseActivity<ActivityHighVerticalBinding>(
    R.layout.activity_high_vertical
) {
    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = MultiViewTypeAdapter(viewModel)
        binding.rvImages.adapter = adapter
        binding.rvImages.setHasFixedSize(true)
        binding.rvImages.addItemDecoration(ImageItemDecoration())

        val layoutManager = GridLayoutManager(this, 6, GridLayoutManager.VERTICAL, false)
        adapter.setLayoutManger(layoutManager)
        binding.rvImages.layoutManager = layoutManager
        viewModel.loadHighRandomImagesAndUserImages(30)
        setObserve()
    }

    private fun setObserve() {
        viewModel.recyclerItems.observe(this) {
            (binding.rvImages.adapter as? MultiViewTypeAdapter)?.replaceItems(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }
}
