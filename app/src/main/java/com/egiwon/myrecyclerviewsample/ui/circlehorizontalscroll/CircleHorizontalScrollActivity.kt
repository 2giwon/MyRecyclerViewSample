package com.egiwon.myrecyclerviewsample.ui.circlehorizontalscroll

import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
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

    private var orientationHelper: OrientationHelper? = null

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
            scrollToCenter(it.size * 1000, binding.rvCircleScroll.layoutManager as LinearLayoutManager)

        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun scrollToCenter(position: Int, layoutManger: LinearLayoutManager) {
        binding.rvCircleScroll.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val view = layoutManger.getChildAt(0)
                if (view != null) {
                    val viewSize = getOrientationHelper(layoutManger)?.getDecoratedEnd(view)
                    layoutManger.scrollToPositionWithOffset(position, viewSize?.div(4) ?: 0)
                }


                binding.rvCircleScroll.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun getOrientationHelper(layoutManger: LinearLayoutManager): OrientationHelper? {
        if (orientationHelper == null) {
            orientationHelper = OrientationHelper.createHorizontalHelper(layoutManger)
        }

        return orientationHelper
    }
}
