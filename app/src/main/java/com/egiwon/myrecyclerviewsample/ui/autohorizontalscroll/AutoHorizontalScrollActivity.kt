package com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.myrecyclerviewsample.R
import com.egiwon.myrecyclerviewsample.base.BaseActivity
import com.egiwon.myrecyclerviewsample.databinding.ActivityAutoScrollBinding
import com.egiwon.myrecyclerviewsample.ext.dpToPx
import com.egiwon.myrecyclerviewsample.ui.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AutoHorizontalScrollActivity : BaseActivity<ActivityAutoScrollBinding>(
    R.layout.activity_auto_scroll
) {

    private val viewModel: ImageViewModel by viewModels()

    private var orientationHelper: OrientationHelper? = null

//    private val itemDp: Int by lazy {
//        this@AutoHorizontalScrollActivity.dpToPx(200f).toInt()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        makeAutoScrollAdapter()
        makeAutoScrollAdapter2()

        viewModel.loadRandomImages(10)
        setObserve()
    }

    private fun makeAutoScrollAdapter2() {
        binding.rvImages2.adapter = AutoScrollAdapter2()
        binding.rvImages2.setHasFixedSize(true)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvImages2)

    }

    private tailrec suspend fun autoScrollImageList() {
        val adapter = (binding.rvImages2.adapter as AutoScrollAdapter2)
        val size: Int = adapter.currentList.size
        val layoutManager = binding.rvImages2.layoutManager as LinearLayoutManager
        val firstPosition = layoutManager.findFirstVisibleItemPosition()

        if (firstPosition + THRESHOLD < size) {
            binding.rvImages2.smoothScrollToPosition(getNextPosition(size, layoutManager))
            delay(DELAY_BETWEEN_SCROLL_MS)
        } else {

            if (firstPosition != RecyclerView.NO_POSITION) {
                val currentList = adapter.currentList
                val secondPart = currentList.subList(0, firstPosition)
                val firstPart = currentList.subList(firstPosition, currentList.size)
                adapter.submitList(firstPart + secondPart)
            }
            delay(500)
        }
        autoScrollImageList()
    }

    private fun getNextPosition(itemSize: Int, layoutManager: LinearLayoutManager): Int {
        val firstPosition = layoutManager.findFirstCompletelyVisibleItemPosition()
        val lastPosition = layoutManager.findLastCompletelyVisibleItemPosition()

        val position = if (firstPosition == lastPosition) firstPosition else lastPosition
        return (position + 1) % itemSize
    }

    private fun makeAutoScrollAdapter() {
        binding.rvImages.adapter = AutoHorizontalScrollAdapter(
            R.layout.item_scroll_image
        )

        binding.rvImages.setHasFixedSize(true)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvImages)
    }

    private fun setObserve() {
        viewModel.photos.observe(this, {
            (binding.rvImages.adapter as? AutoHorizontalScrollAdapter)?.replaceItems(it)

            binding.rvImages.autoScroll(it.size)

            (binding.rvImages2.adapter as AutoScrollAdapter2).submitList(it)
            lifecycleScope.launch { autoScrollImageList() }
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onDestroy() {
        binding.rvImages.stopAutoScroll()
        super.onDestroy()
    }

    companion object {
        const val DELAY_BETWEEN_SCROLL_MS = 2000L
        const val DIRECTION_RIGHT = 1
        const val THRESHOLD = 4
    }
}
