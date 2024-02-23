package id.go.ngawikab.siketan.presentation.announcement.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.setImageUrl
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.databinding.ItemFarmInfoBinding
import id.go.ngawikab.siketan.domain.farm.model.response.EventTani
import id.go.ngawikab.siketan.domain.farm.model.response.InfoTani
import id.go.ngawikab.siketan.utils.dateFormat


/**
 * Created by Wahid on 6/16/2023.
 * Github github.com/wahidabd.
 */


class AnnouncementAdapter(
    private val context: Context,
    private val onClick:(InfoTani)->Unit,
    item: ArrayList<InfoTani> = arrayListOf(),
) : BaseAsyncRecyclerAdapter<InfoTani, AnnouncementAdapter.AnnouncementViewHolder>(item) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemFarmInfoBinding.inflate(context.layoutInflater, parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        return AnnouncementViewHolder(getViewBinding(parent, viewType))
    }

    inner class AnnouncementViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<InfoTani>(binding) {
        override fun bind(data: InfoTani) = with(binding as ItemFarmInfoBinding) {
            tvTitle.text = context.getString(R.string.format_underline, data.judul)
            tvAuthor.text = context.getString(R.string.format_label_author, data.createdBy)
            tvDate.text = data.createdAt?.dateFormat()
            tvToMore.setOnClickListener {
                onClick(data)
            }

            imgImage.setImageUrl(
                context,
                data.fotoBerita.toString(),
                placeholder = R.drawable.ic_image_placeholder
            )
        }
    }

}