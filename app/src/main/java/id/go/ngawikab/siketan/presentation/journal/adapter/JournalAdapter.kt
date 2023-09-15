package id.go.ngawikab.siketan.presentation.journal.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.setImageUrl
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.databinding.ItemFarmInfoBinding
import id.go.ngawikab.siketan.domain.auth.model.User
import id.go.ngawikab.siketan.utils.dateFormat


/**
 * Created by Wahid on 7/21/2023.
 * Github github.com/wahidabd.
 */


class JournalAdapter(
    private val context: Context,
    items: ArrayList<User> = arrayListOf(),
) : BaseAsyncRecyclerAdapter<User, JournalAdapter.JournalViewHolder>(items) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemFarmInfoBinding.inflate(context.layoutInflater, parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JournalViewHolder {
        return JournalViewHolder(getViewBinding(parent, viewType))
    }

    inner class JournalViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<User>(binding) {
        override fun bind(data: User) =
            with(binding as ItemFarmInfoBinding) {
                val journal = data.jurnalHarian
                tvTitle.text = context.getString(R.string.format_underline, journal?.judul)
                tvDate.text = journal?.tanggalDibuat?.dateFormat()
                tvStatus.text = journal?.statusJurnal
                tvAuthor.text = context.getString(R.string.format_label_author, data.nama)

                imgImage.setImageUrl(
                    context,
                    journal?.gambar.toString(),
                    placeholder = R.drawable.ic_image_placeholder,
                    isCenterCrop = true
                )
            }
    }
}