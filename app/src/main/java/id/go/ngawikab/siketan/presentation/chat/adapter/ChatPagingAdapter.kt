package id.go.ngawikab.siketan.presentation.chat.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.isNotNull
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visibleIf
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.data.farm.model.farm.response.Petani
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerData
import id.go.ngawikab.siketan.databinding.ItemChatBinding
import id.go.ngawikab.siketan.databinding.ItemTanamanBinding
import id.go.ngawikab.siketan.domain.auth.model.User
import id.go.ngawikab.siketan.utils.formatPhoneNumber

class ChatPagingAdapter(
    private val context: Context,
    private val pref: User,
): PagingDataAdapter<Petani, ChatPagingAdapter.ViewHolder>(
    diffUtilCallback
) {

    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<Petani>() {
            override fun areItemsTheSame(
                oldItem: Petani,
                newItem: Petani
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Petani,
                newItem: Petani
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<Petani>(binding) {
        override fun bind(data: Petani) = with(binding as ItemChatBinding) {
            tvNama.text = data.nama
            tvNoTelp.text = data.noTelp
            tvDesa.text = "Desa "+data.desaData?.nama.toString()
            tvKecamatan.text = "Kec "+data.kecamatanData?.nama.toString()
            btnCall.onClick {
                val contactNumber = formatPhoneNumber(data.noTelp)
                val message = "Hi, Bapak/Ibu "+data.nama+", saya "+pref.nama+" saya ingin menanyakan tentang ... "
                val uri = "whatsapp://send?phone=${contactNumber}&text=${Uri.encode(message)}"
                try{
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(uri)
                    context.startActivity(intent)
                }catch (e:Exception){
                    showToast("Whatsapp wasn't installed!")
                }
            }
        }
    }
}