package com.wahidabd.siketan.presentation.announcement.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.siketan.R
import com.wahidabd.siketan.databinding.ItemEventBinding
import com.wahidabd.siketan.domain.farm.model.response.EventTani
import com.wahidabd.siketan.utils.dateFormat


/**
 * Created by Wahid on 6/20/2023.
 * Github github.com/wahidabd.
 */


class EventAdapter(
    private val context: Context,
    items: ArrayList<EventTani> = arrayListOf()
) : BaseAsyncRecyclerAdapter<EventTani, EventAdapter.EventViewHolder>(items) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemEventBinding.inflate(context.layoutInflater, parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventAdapter.EventViewHolder {
        return EventViewHolder(getViewBinding(parent, viewType))
    }

    inner class EventViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<EventTani>(binding) {
        override fun bind(data: EventTani) = with(binding as ItemEventBinding) {
            tvTitle.text = data.namaKegiatan
            tvAuthor.text = data.tanggalAcara?.dateFormat()
            tvToMore.text = context.getString(R.string.format_label_location, data.tempat)
            tvStartDate.text = context.getString(
                R.string.format_label_start_date,
                data.waktuAcara?.substring(0, 5)
            )
            tvEndDate.text =
                context.getString(R.string.format_label_end_date, data.waktuAcara?.substring(8, 13))

            imgImage.setImageUrl(
                context,
                data.fotoKegiatan.toString(),
                placeholder = R.drawable.ic_image_placeholder
            )
        }
    }
}