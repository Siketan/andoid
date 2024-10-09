package id.go.ngawikab.siketan.presentation.announcement

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.databinding.FragmentAnnouncementDetailBinding
import id.go.ngawikab.siketan.domain.farm.model.response.InfoTani
import id.go.ngawikab.siketan.utils.dateFormat

class AnnouncementDetailFragment : BaseFragment<FragmentAnnouncementDetailBinding>() {

    private val data: AnnouncementDetailFragmentArgs by navArgs()


    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentAnnouncementDetailBinding {
        return FragmentAnnouncementDetailBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun initUI() {
        val data: InfoTani = data.info
        with(binding) {
            imageView.setImageUrl(
                requireContext(),
                data.fotoBerita.toString(),
                placeholder = R.drawable.ic_image_placeholder
            )
            tvCategory.text = data.kategori
            tvDate.text = data.tanggal?.dateFormat()
            tvTitle.text = data.judul
            tvAuthor.text = "Dibuat oleh: " + data.createdBy
            tvDesc.text=Html.fromHtml(data.isi,Html.FROM_HTML_MODE_LEGACY)
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