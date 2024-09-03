package id.go.ngawikab.siketan.presentation.journal

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import id.go.ngawikab.siketan.R
import id.go.ngawikab.siketan.data.farm.model.journal.Jounal
import id.go.ngawikab.siketan.databinding.FragmentJournalDetailBinding
import id.go.ngawikab.siketan.domain.farm.model.response.Journal
import id.go.ngawikab.siketan.utils.dateFormat

class JournalDetailFragment : BaseFragment<FragmentJournalDetailBinding>() {

    private val data: JournalDetailFragmentArgs by navArgs()
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentJournalDetailBinding {
        return FragmentJournalDetailBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        val data: Jounal = data.journal
        with(binding) {
            imageView.setImageUrl(
                requireContext(),
                data.gambar.toString(),
                placeholder = R.drawable.ic_image_placeholder
            )
            tvTitle.text = context?.getString(R.string.format_underline, data.judul)
            tvDesc.text = Html.fromHtml(data.uraian, Html.FROM_HTML_MODE_LEGACY)
            tvAuthor.text = context?.getString(R.string.format_label_author, data.dataPenyuluh?.nama)
            tvPlace.text = data.tanggalDibuat?.dateFormat()
            tvTime.text = data.statusJurnal
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