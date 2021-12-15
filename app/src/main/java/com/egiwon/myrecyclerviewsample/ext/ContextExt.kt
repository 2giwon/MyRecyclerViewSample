package com.egiwon.myrecyclerviewsample.ext

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified ACTIVITY : Activity> Context.startActivity() {
    startActivity(Intent(this, ACTIVITY::class.java))
}
