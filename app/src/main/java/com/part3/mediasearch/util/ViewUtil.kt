package com.part3.mediasearch.util

import android.icu.text.SimpleDateFormat
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.part3.mediasearch.R
import java.util.Date

object ViewUtil {
    fun ImageView.setThumbnail(thumbnailUrl: String?) {
        thumbnailUrl?.let {
            load(thumbnailUrl) { crossfade(300) }
        }
    }

    fun ImageView.setFavorite(isFavorite: Boolean) {
        load(if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
    }

    fun TextView.setDate(dateTime: Date?) {
        dateTime?.let {
            text = SimpleDateFormat("YYYY.MM.dd HH:mm:ss").format(dateTime)
        }
    }
}