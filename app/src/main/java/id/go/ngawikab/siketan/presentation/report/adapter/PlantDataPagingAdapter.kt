package id.go.ngawikab.siketan.presentation.report.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.isNotNull
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import com.wahidabd.library.utils.exts.visibleIf
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerData
import id.go.ngawikab.siketan.data.farm.model.store.ProductResponse
import id.go.ngawikab.siketan.databinding.ItemStoreBinding
import id.go.ngawikab.siketan.databinding.ItemTanamanBinding
import id.go.ngawikab.siketan.utils.toCurrency

class PlantDataPagingAdapter(
    private val context: Context,
    private val onItemClick: (PlantFarmerData) -> Unit,
    private val onMoreClick: (PlantFarmerData) -> Unit
) : PagingDataAdapter<PlantFarmerData, PlantDataPagingAdapter.ViewHolder>(
    diffUtilCallback
) {

    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<PlantFarmerData>() {
            override fun areItemsTheSame(
                oldItem: PlantFarmerData,
                newItem: PlantFarmerData
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PlantFarmerData,
                newItem: PlantFarmerData
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
        val binding = ItemTanamanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<PlantFarmerData>(binding) {
        override fun bind(data: PlantFarmerData) = with(binding as ItemTanamanBinding) {
            tvJenisPanen.visibleIf { data.jenis.isNotNull() }

            tvName.text = data.komoditas
            tvKategori.text = data.kategori
            tvLuasLahan.text = context.getString(R.string.format_luas_lahan, data.luasLahan)
            tvBulanTanam.text = context.getString(R.string.format_tanggal, data.prakiraanBulanPanen)
            tvJenisPanen.text = context.getString(R.string.format_jenis_panen, data.jenis)

            cardRoot.onClick { onItemClick.invoke(data) }
        }
    }
}