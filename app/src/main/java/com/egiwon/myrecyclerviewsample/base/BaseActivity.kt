package com.egiwon.myrecyclerviewsample.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB: ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
): AppCompatActivity() {

    protected lateinit var binding: VDB
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
    }
}
