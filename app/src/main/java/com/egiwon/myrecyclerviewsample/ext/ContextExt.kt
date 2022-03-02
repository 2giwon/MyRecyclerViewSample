package com.egiwon.myrecyclerviewsample.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.TypedValue

inline fun <reified ACTIVITY : Activity> Context.startActivity() {
    startActivity(Intent(this, ACTIVITY::class.java))
}

fun Context.dpToPx(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
}
