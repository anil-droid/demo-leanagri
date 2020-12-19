package com.demo.leanagri.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.demo.leanagri.R
import com.demo.leanagri.utils.formatDate
import com.demo.leanagri.utils.toOriginal
import com.demo.leanagri.utils.toThumb
import com.google.gson.internal.LinkedTreeMap
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.util.*


@BindingAdapter("imageUrlThumb")
fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso.get()
        .load(imageUrl?.toThumb())
        .placeholder(R.drawable.placeholder).noFade()
        .into(view, object : Callback {
            override fun onSuccess() {
                view.alpha = 0f
                view.animate().setDuration(500).alpha(1f).start()
            }

            override fun onError(e: Exception) {}
        })
}

@BindingAdapter("imageUrlOriginal")
fun loadImageOriginal(view: ImageView, imageUrlOriginal: String?) {
    Picasso.get()
        .load(imageUrlOriginal?.toOriginal())
        .placeholder(R.drawable.placeholder).noFade()
        .into(view, object : Callback {
            override fun onSuccess() {
                view.alpha = 0f
                view.animate().setDuration(500).alpha(1f).start()
            }

            override fun onError(e: Exception) {}
        })
}

@BindingAdapter("duration")
fun formatDuration(view: TextView, duration: Int?) {

    duration?.let {

        val hour = it / 60
        val minute = it % 60
        view.text = view.resources?.getString(R.string.hour_and_min, hour, minute)

    }

}


@BindingAdapter("toCompanyData")
fun toCompanyData(view: TextView, data: ArrayList<LinkedTreeMap<Any, Any>>?) {

    view.text = StringBuilder().run {
        data?.forEach { spokenLng ->

            if (isNotEmpty()) append(", ")

            append("${spokenLng["name"]}  (${spokenLng["origin_country"]})")
        }

        toString()
    }


}


@BindingAdapter("toSpokenLangData")
fun toSpokenLangData(view: TextView, data: ArrayList<LinkedTreeMap<Any, Any>>?) {

    view.text = StringBuilder().run {
        data?.forEach { spokenLng ->

            if (isNotEmpty()) append(", ")

            append("${spokenLng["name"]}")
        }

        toString()
    }

}


@BindingAdapter("date")
fun formatDate(
    view: TextView,
    strCurrentDate: String?
) {

    view.text = strCurrentDate?.formatDate()
}






