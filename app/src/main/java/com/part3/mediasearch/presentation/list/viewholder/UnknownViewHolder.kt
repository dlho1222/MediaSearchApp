package com.part3.mediasearch.presentation.list.viewholder

import com.part3.mediasearch.databinding.ItemEmptyBinding
import com.part3.mediasearch.presentation.model.SearchItem

class UnknownViewHolder(private val binding: ItemEmptyBinding) :
    BindingViewHolder<ItemEmptyBinding>(binding) {
    override fun bind(item: SearchItem) {
        super.bind(item)
    }
}