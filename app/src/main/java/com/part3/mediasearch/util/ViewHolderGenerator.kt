package com.part3.mediasearch.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.part3.mediasearch.presentation.list.viewholder.BindingViewHolder
import com.part3.mediasearch.presentation.list.viewholder.ImageViewHolder
import com.part3.mediasearch.presentation.list.viewholder.UnknownViewHolder
import com.part3.mediasearch.presentation.list.viewholder.VideoViewHolder
import com.part3.mediasearch.presentation.model.SearchItem
import com.part3.mediasearch.presentation.model.ViewType

object ViewHolderGenerator {
    fun get(
        parent: ViewGroup,
        viewType: Int,
        onClick: (SearchItem) -> Unit
    ): BindingViewHolder<*> = when (viewType) {
        ViewType.VIDEO.ordinal -> VideoViewHolder(parent.toBinding(),onClick)
        ViewType.IMAGE.ordinal -> ImageViewHolder(parent.toBinding(),onClick)
        else -> UnknownViewHolder(parent.toBinding())
    }

    private inline fun <reified V : ViewBinding> ViewGroup.toBinding(): V = V::class.java.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    ).invoke(null, LayoutInflater.from(context), this, false) as V
}