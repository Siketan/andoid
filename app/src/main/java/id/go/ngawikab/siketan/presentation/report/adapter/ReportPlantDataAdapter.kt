package go.ngawikab.siketan.presentation.report.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.library.utils.exts.setImageUrl
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.databinding.ItemReportTanamanBinding
import id.go.ngawikab.siketan.utils.dateFormat
import id.go.ngawikab.siketan.data.farm.model.farm.response.report.ReportTanamanDataResponse


/**
 * Created by Wahid on 8/19/2023.
 * Github github.com/wahidabd.
 */


class ReportPlantDataAdapter(
    private val context: Context,
    items: ArrayList<ReportTanamanDataResponse> = arrayListOf(),
) : BaseAsyncRecyclerAdapter<ReportTanamanDataResponse, ReportPlantDataAdapter.ViewHolder>(items) {
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemReportTanamanBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReportPlantDataAdapter.ViewHolder {
        return ViewHolder(getViewBinding(parent, viewType))
    }

    inner class ViewHolder(binding: ViewBinding) :
        BaseAsyncItemViewHolder<ReportTanamanDataResponse>(binding) {
        override fun bind(data: ReportTanamanDataResponse) =
            with(binding as ItemReportTanamanBinding) {
                tvKondisi.text = data.komdisiTanaman
                tvTanggal.text =
                    context.getString(R.string.format_tanggal, data.tanggalLaporan?.dateFormat())
                tvDeskripsi.text = data.deskripsi
                imgTanaman.setImageUrl(
                    context,
                    data.fotoTanaman.toString(),
                    R.drawable.placeholder,
                    true
                )
            }
    }
}