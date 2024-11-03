package com.part3.mediasearch.presentation.list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.part3.mediasearch.presentation.list.viewholder.BindingViewHolder
import com.part3.mediasearch.presentation.model.SearchItem
import com.part3.mediasearch.util.DiffCallBack
import com.part3.mediasearch.util.ViewHolderGenerator

class ListAdapter : ListAdapter<SearchItem, BindingViewHolder<*>>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<*> {
        return ViewHolderGenerator.get(parent, viewType)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<*>, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position]?.viewType?.ordinal ?: -1
    }
}