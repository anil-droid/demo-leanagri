package com.demo.leanagri.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun String.toThumb(): String {

    return "https://image.tmdb.org/t/p/w500/$this"
}

fun String.toOriginal(): String {

    return "https://image.tmdb.org/t/p/original/$this"
}

fun String.formatDate() = try {
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        .run {
            parse(this@formatDate)
        }?.let {
            SimpleDateFormat("MMM, dd yyyy", Locale.getDefault()).run {
                format(it)
            }
        } ?: ""
}catch (e: Exception){
    ""
}

fun Array<out View>.setOnClickListener(ctx: View.OnClickListener)
{

    this.forEach {
        it.setOnClickListener(ctx)
    }
}

fun String.desc() = this.plus(".desc")
fun String.asc() = this.plus(".asc")
