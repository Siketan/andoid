package com.wahidabd.siketan.utils.components

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.wahidabd.library.presentation.fragment.BaseDialogFragment
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.siketan.R
import com.wahidabd.siketan.databinding.FragmentMyDialogBinding

class MyDialogFragment : BaseDialogFragment<FragmentMyDialogBinding>() {

    companion object {
        fun newInstance(
            dialogType: MyDialogType,
            onButtonClicked: (() -> Unit)? = null
        ): MyDialogFragment = MyDialogFragment().apply {
            this.type = dialogType
            this.onButtonClicked = onButtonClicked
        }
    }

    private var type: MyDialogType = MyDialogType.ABOUT
    private var onButtonClicked: (() -> Unit)? = null

    override val isDialogCancelable: Boolean = true
    override val tagName: String = MyDialogFragment::class.java.name

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentMyDialogBinding {
        return FragmentMyDialogBinding.inflate(layoutInflater)
    }

    override fun initUI() {
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.90F).toInt(),
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_rounded_white_10)

        setupType()
    }

    override fun initAction() {
        with(binding) {
            btn.onClick {
                dismiss()
                onButtonClicked?.invoke()
            }
        }
    }

    override fun initProcess() {}

    private fun setupType() = with(binding) {
        when (type) {
            MyDialogType.MAINTENANCE -> {
                imgHeader.setImageResource(R.drawable.img_maintenance)
                tvContent.text = getString(R.string.message_maintenance)
                tvContent.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow))
                btn.text = getString(R.string.label_close)
                btn.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.pink)
            }

            MyDialogType.ABOUT -> {
                imgHeader.setImageResource(R.drawable.img_about)
                tvContent.text = getString(R.string.message_about)
                tvContent.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary))
                btn.text = getString(R.string.label_close)
                btn.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.pink)
            }

            MyDialogType.SAVE -> {
                imgHeader.setImageResource(R.drawable.img_save)
                tvContent.text = getString(R.string.format_message_success)
                tvContent.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow))
                btn.text = getString(R.string.label_close)
                btn.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.secondary)
            }

            MyDialogType.CANCEL -> {
                imgHeader.setImageResource(R.drawable.img_reject)
                tvContent.text = getString(R.string.message_cancel_input)
                tvContent.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                btn.text = getString(R.string.label_cancel)
                btn.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.pink)
            }
        }
    }

    enum class MyDialogType {
        MAINTENANCE,
        ABOUT,
        SAVE,
        CANCEL
    }

}