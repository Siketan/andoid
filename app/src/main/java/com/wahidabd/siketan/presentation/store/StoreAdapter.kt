package com.wahidabd.siketan.presentation.store

import android.content.Context
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.layoutInflater
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.siketan.R
import com.wahidabd.siketan.databinding.ItemStoreBinding
import com.wahidabd.siketan.domain.farm.model.response.Product
import com.wahidabd.siketan.utils.toCurrency


/**
 * Created by Wahid on 6/18/2023.
 * Github github.com/wahidabd.
 */


class StoreAdapter(
    private val context: Context,
    items: ArrayList<Product> = arrayListOf(),
) : BaseAsyncRecyclerAdapter<Product, StoreAdapter.StoreViewHolder>(items) {

    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemStoreBinding.inflate(context.layoutInflater, parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoreAdapter.StoreViewHolder {
        return StoreViewHolder(getViewBinding(parent, viewType))
    }

    inner class StoreViewHolder(binding: ViewBinding) : BaseAsyncItemViewHolder<Product>(binding) {
        override fun bind(data: Product) = with(binding as ItemStoreBinding) {
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