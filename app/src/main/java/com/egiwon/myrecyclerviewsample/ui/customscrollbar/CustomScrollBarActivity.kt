package com.egiwon.myrecyclerviewsample.ui.customscrollbar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.base.BaseActivity
import com.egiwon.myrecyclerviewsample.databinding.ActivityCustomScrollBinding
import com.egiwon.myrecyclerviewsample.ui.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomScrollBarActivity : BaseActivity<ActivityCustomScrollBinding>(
    R.layout.activity_custom_scroll
) {
    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadRandomImages(10)
        setObserve()
    }

    private fun setObserve() {
        viewModel.photos.observe(this, {

        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}
