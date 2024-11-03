package com.part3.mediasearch.presentation.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.part3.mediasearch.presentation.model.SearchItem

abstract class BindingViewHolder<VB : ViewBinding>(binding: VB) :
    RecyclerView.ViewHolder(binding.root) {
    protected var item: SearchItem? = null
    open fun bind(item: SearchItem) {
        this.item = item
    }
}