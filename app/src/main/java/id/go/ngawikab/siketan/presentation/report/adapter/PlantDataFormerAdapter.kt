package go.ngawikab.siketan.presentation.report.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.isNotNull
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visibleIf
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerData
import id.go.ngawikab.siketan.databinding.ItemTanamanBinding


/**
 * Created by Wahid on 8/18/2023.
 * Github github.com/wahidabd.
 */


class PlantDataFormerAdapter(
    private val context: Context,
    items: ArrayList<PlantFarmerData> = arrayListOf(),
    private val onItemClick: (PlantFarmerData) -> Unit,
    private val onMoreClick: (PlantFarmerData) -> Unit
): BaseAsyncRecyclerAdapter<PlantFarmerData, PlantDataFormerAdapter.PlantDataFormerViewHolder>(items){
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemTanamanBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlantDataFormerAdapter.PlantDataFormerViewHolder {
        return PlantDataFormerViewHolder(getViewBinding(parent, viewType))
    }

    inner class PlantDataFormerViewHolder(binding: ViewBinding): BaseAsyncItemViewHolder<PlantFarmerData>(binding){
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