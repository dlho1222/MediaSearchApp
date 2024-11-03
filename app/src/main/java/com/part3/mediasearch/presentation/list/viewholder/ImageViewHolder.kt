package com.part3.mediasearch.presentation.list.viewholder

import com.part3.mediasearch.databinding.ItemImageBinding
import com.part3.mediasearch.presentation.model.ImageItem
import com.part3.mediasearch.presentation.model.SearchItem
import com.part3.mediasearch.util.ViewUtil.setDate
import com.part3.mediasearch.util.ViewUtil.setFavorite
import com.part3.mediasearch.util.ViewUtil.setThumbnail

class ImageViewHolder(private val binding: ItemImageBinding) :
    BindingViewHolder<ItemImageBinding>(binding) {
    override fun bind(item: SearchItem) {
        super.bind(item)
        if (item is ImageItem) {
            with(binding) {
                ivThumbnail.setThumbnail(item.thumbnailUrl)
                ivFavorite.setFavorite(item.isFavorite)
                tvDate.setDate(item.dateTime)
                tvUrl.text = item.docUrl
                tvCollection.text = item.collection
                tvSiteName.text = item.displaySiteName
            }
        }
    }
}