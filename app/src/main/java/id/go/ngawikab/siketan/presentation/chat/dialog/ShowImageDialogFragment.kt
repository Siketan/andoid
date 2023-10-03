package id.go.ngawikab.siketan.presentation.chat.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.wahidabd.library.presentation.fragment.BaseDialogFragment
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.setImageUrl
import id.go.ngawikab.siketan.databinding.FragmentShowImageDialogBinding

class ShowImageDialogFragment : BaseDialogFragment<FragmentShowImageDialogBinding>() {

    companion object {
        fun newInstance(imageUrl: String): ShowImageDialogFragment =
            ShowImageDialogFragment().apply {
                this.imageUrl = imageUrl
            }
    }

    private var imageUrl: String? = emptyString()

    override val isDialogCancelable: Boolean = true
    override val tagName: String = ShowImageDialogFragment::class.java.name

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentShowImageDialogBinding =
        FragmentShowImageDialogBinding.inflate(layoutInflater)

    override fun initUI() {
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.90F).toInt(),
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun initAction() {}

    override fun initProcess() = with(binding) {
        imageView.setImageUrl(requireContext(), imageUrl.toString())
    }

}