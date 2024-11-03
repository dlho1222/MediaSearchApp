package com.part3.mediasearch.util

import androidx.recyclerview.widget.DiffUtil
import com.part3.mediasearch.presentation.model.SearchItem

class DiffCallBack : DiffUtil.ItemCallback<SearchItem>() {
    override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean =
        oldItem.getKey() == newItem.getKey() && oldItem.viewType == newItem.viewType

    override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean =
        oldItem == newItem
}