package com.wahidabd.siketan.utils.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.wahidabd.library.presentation.fragment.BaseDialogFragment
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.siketan.R
import com.wahidabd.siketan.databinding.FragmentMyDialogBinding

class MyDialogFragment : BaseDialogFragment<FragmentMyDialogBinding>() {

    companion object {
        fun newInstance(
            dialogType: MyDialogType,
        ): MyDialogFragment = MyDialogFragment().apply {
            this.type = dialogType
        }
    }

    private var type: MyDialogType = MyDialogType.ABOUT

    override val isDialogCancelable: Boolean = false
    override val tagName: String = MyDialogFragment::class.java.name

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentMyDialogBinding = FragmentMyDialogBinding.inflate(layoutInflater)

    override fun initUI() {
        setupType()
    }

    override fun initAction() {
        with(binding){
            btn.onClick { dismiss() }
        }
    }

    override fun initProcess() {}

    private fun setupType() = with(binding){
        when(type) {
            MyDialogType.MAINTENANCE -> {
                imgHeader.setImageResource(R.drawable.img_maintenance)
                tvContent.text = getString(R.string.message_maintenance)
                btn.text = getString(R.string.label_close)
                btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.pink)
            }
            MyDialogType.ABOUT -> {
                imgHeader.setImageResource(R.drawable.img_about)
                tvContent.text = getString(R.string.message_about)
                btn.text = getString(R.string.label_close)
                btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.pink)
            }
            MyDialogType.SAVE -> {
                imgHeader.setImageResource(R.drawable.img_save)
                tvContent.text = getString(R.string.format_message_success)
                btn.text = getString(R.string.label_close)
                btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.secondary)
            }
            MyDialogType.CANCEL -> {
                imgHeader.setImageResource(R.drawable.img_reject)
                tvContent.text = getString(R.string.message_cancel_input)
                btn.text = getString(R.string.label_cancel)
                btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.pink)
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