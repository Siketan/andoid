package id.go.ngawikab.siketan.presentation.store

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.setImageUrl
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.data.farm.model.store.ProductResponse
import id.go.ngawikab.siketan.databinding.ItemStoreBinding
import id.go.ngawikab.siketan.domain.farm.model.response.Product
import id.go.ngawikab.siketan.utils.toCurrency

class StorePagingAdapter(private val context: Context): PagingDataAdapter<ProductResponse, StorePagingAdapter.ViewHolder>(
    diffUtilCallback
) {

    companion object{
        val diffUtilCallback = object : DiffUtil.ItemCallback<ProductResponse>() {
            override fun areItemsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ProductResponse, newItem: ProductResponse): Boolean =
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
        val binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder( binding: ViewBinding) : BaseAsyncItemViewHolder<ProductResponse>(binding){
        override fun bind(data: ProductResponse) = with(binding as ItemStoreBinding) {
            imgProduct.setImageUrl(
                context,
                data.fotoTanaman.toString(),
                placeholder = R.drawable.ic_image_placeholder
            )
            tvStock.text = context.getString(R.string.format_label_stock, data.stok.toString())
            tvPrice.text = context.getString(
                R.string.format_label_price,
                data.harga?.toDouble()?.toCurrency()
            )
//            tvPrice.text = data.harga
            tvTitle.text = data.namaProducts
        }
    }
}