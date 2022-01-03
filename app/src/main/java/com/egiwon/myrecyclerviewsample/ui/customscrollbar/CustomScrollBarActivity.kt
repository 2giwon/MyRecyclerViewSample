package com.egiwon.myrecyclerviewsample.ui.customscrollbar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        binding.rvImages.adapter = CustomScrollAdapter(
            R.layout.item_scroll_image
        )
        binding.rvImages.setHasFixedSize(true)
        binding.rvImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                scrollByRecyclerView(recyclerView)
            }
        })

        setScrollBarVisibility()
        viewModel.loadRandomImages(10)
        setObserve()
    }

    private fun scrollByRecyclerView(recyclerView: RecyclerView) {
        val range = recyclerView.computeHorizontalScrollRange()

        val offset = recyclerView.computeHorizontalScrollOffset()
        val extent = recyclerView.computeHorizontalScrollExtent()
        val proportion = (offset * 1.0f / (range - extent))
        val transMaxRange = binding.layoutScroll.width - binding.viewScrollBar.width

        binding.viewScrollBar.translationX = transMaxRange * proportion
    }

    private fun setScrollBarVisibility() {
        binding.rvImages.layoutManager = object : LinearLayoutManager(this, HORIZONTAL, false) {
            override fun onLayoutCompleted(state: RecyclerView.State?) {
                super.onLayoutCompleted(state)
                val firstVisibleItemsPosition = findFirstVisibleItemPosition()
                val lastVisibleItemPosition = findLastVisibleItemPosition()
                val itemsShown = lastVisibleItemPosition - firstVisibleItemsPosition

                binding.layoutScroll.visibility =
                    if (binding.rvImages.adapter?.itemCount ?: 0 > itemsShown) View.VISIBLE else View.INVISIBLE
            }
        }

    }

    private fun setObserve() {
        viewModel.photos.observe(this, {
            (binding.rvImages.adapter as? CustomScrollAdapter)?.replaceItems(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}
