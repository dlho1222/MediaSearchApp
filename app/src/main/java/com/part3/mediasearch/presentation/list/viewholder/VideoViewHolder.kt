package com.part3.mediasearch.presentation.list.viewholder

import com.part3.mediasearch.databinding.ItemVideoBinding
import com.part3.mediasearch.presentation.model.SearchItem
import com.part3.mediasearch.presentation.model.VideoItem
import com.part3.mediasearch.util.ViewUtil.setDate
import com.part3.mediasearch.util.ViewUtil.setFavorite
import com.part3.mediasearch.util.ViewUtil.setThumbnail

class VideoViewHolder(
    private val binding: ItemVideoBinding,
    private val onClick: (SearchItem) -> Unit
) :
    BindingViewHolder<ItemVideoBinding>(binding) {
    override fun bind(item: SearchItem) {
        super.bind(item)
        if (item is VideoItem) {
            with(binding) {
                ivThumbnail.setThumbnail(item.thumbnailUrl)
                ivFavorite.apply {
                    setFavorite(item.isFavorite)
                    setOnClickListener { onClick(item) }
                }
                tvTitle.text = item.title
                tvAuthor.text = item.author
                tvDate.setDate(item.dateTime)
                tvPlayTime.text = item.playTime.toString()
            }
        }
    }
}