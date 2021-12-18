package com.egiwon.myrecyclerviewsample.ui.autohorizontalscroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class AutoScrollRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var dispose: Disposable? = null

    private val linearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    init {
        this.layoutManager = linearLayoutManager
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        if (e?.action == MotionEvent.ACTION_DOWN) {
            stopAutoScroll()
        } else if (e?.action == MotionEvent.ACTION_UP || e?.action == MotionEvent.ACTION_CANCEL) {
            adapter?.let { autoScroll(it.itemCount) }
        }
        return super.onTouchEvent(e)
    }

    private fun autoScroll(itemSize: Int) {
        dispose?.let {
            if (!it.isDisposed) return
        }

        dispose = Observable.interval(2000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                smoothScrollToPosition(getNextPosition(itemSize))
            }
    }

    private fun getNextPosition(itemSize: Int): Int {
        val position = linearLayoutManager.findFirstCompletelyVisibleItemPosition()

        return (position + 1) % itemSize
    }

    private fun stopAutoScroll() {
        dispose?.let(Disposable::dispose)
    }
}
