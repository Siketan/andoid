package id.go.ngawikab.siketan.presentation.announcement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.databinding.FragmentEventDetailBinding
import id.go.ngawikab.siketan.domain.farm.model.response.EventTani

class EventDetailFragment : BaseFragment<FragmentEventDetailBinding>() {

    private val data: EventDetailFragmentArgs by navArgs()
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentEventDetailBinding {
        return FragmentEventDetailBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        val data: EventTani = data.event
        with(binding) {
            imageView.setImageUrl(
                requireContext(),
                data.fotoKegiatan.toString(),
                placeholder = R.drawable.ic_image_placeholder
            )
            tvTitle.text = data.namaKegiatan
            tvDesc.text = data.isi
            tvAuthor.text = data.peserta
            tvPlace.text= data.tempat
            tvTime.text = data.waktuAcara
        }

    }

    override fun initAction() {
        with(binding) {
            imgBack.onClick { findNavController().navigateUp() }
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

}