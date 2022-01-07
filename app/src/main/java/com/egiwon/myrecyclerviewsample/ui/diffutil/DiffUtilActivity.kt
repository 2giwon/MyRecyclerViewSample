package com.egiwon.myrecyclerviewsample.ui.diffutil

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.base.BaseActivity
import com.egiwon.myrecyclerviewsample.databinding.ActivityDiffutilBinding
import com.egiwon.myrecyclerviewsample.ui.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiffUtilActivity : BaseActivity<ActivityDiffutilBinding>(
    R.layout.activity_diffutil
) {

    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvBefore.adapter = BeforeWithoutDiffUtilAdapter(
            R.layout.item_title_image
        )
        binding.rvBefore.setHasFixedSize(true)

        binding.rvAfter.adapter = AfterWithDiffUtilAdapter(
            R.layout.item_title_image
        )
        binding.rvBefore.setHasFixedSize(true)

        viewModel.loadRandomImages(10)
        setObserve()
    }

    private fun setObserve() {
        viewModel.photos.observe(this, {
            (binding.rvBefore.adapter as? BeforeWithoutDiffUtilAdapter)?.replaceItems(it)
            (binding.rvAfter.adapter as? AfterWithDiffUtilAdapter)?.replaceItems(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}
