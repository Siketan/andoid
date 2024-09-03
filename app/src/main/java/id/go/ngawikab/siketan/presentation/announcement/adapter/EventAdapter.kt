package id.go.ngawikab.siketan.presentation.announcement.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.setImageUrl
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.databinding.ItemEventBinding
import id.go.ngawikab.siketan.domain.farm.model.response.EventTani
import id.go.ngawikab.siketan.utils.dateFormat


/**
 * Created by Wahid on 6/20/2023.
 * Github github.com/wahidabd.
 */


class EventAdapter(
    private val context: Context,
    private val onClick:(EventTani)->Unit,
    items: ArrayList<EventTani> = arrayListOf()
) : BaseAsyncRecyclerAdapter<EventTani, EventAdapter.EventViewHolder>(items) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemEventBinding.inflate(context.layoutInflater, parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventViewHolder {
        return EventViewHolder(getViewBinding(parent, viewType))
    }

    inner class EventViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<EventTani>(binding) {
        override fun bind(data: EventTani) = with(binding as ItemEventBinding) {
            tvTitle.text = data.namaKegiatan
            tvAuthor.text = data.tanggalAcara?.dateFormat()
//            tvToMore.text = context.getString(R.string.format_label_location, data.tempat)
            tvToMore.text = context.getString(R.string.label_read_more)
//            tvStartDate.text = context.getString(
//                R.string.format_label_start_date,
//                data.waktuAcara?.substring(0, 5)
//            )
            tvStartDate.text = context.getString(R.string.format_label_location, data.tempat)
            tvEndDate.text = context.getString(R.string.format_label_end_date, data.waktuAcara)

            imgImage.setImageUrl(
                context,
                data.fotoKegiatan.toString(),
                placeholder = R.drawable.ic_image_placeholder
            )
            binding.root.setOnClickListener {
                onClick(data)
            }
//            tvToMore.setOnClickListener {
//                onClick(data)
//            }
        }
    }
}