package com.wahidabd.siketan.utils.components

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseDialogFragment
import com.wahidabd.siketan.databinding.FragmentMyDialogBinding

class MyDialogFragment : BaseDialogFragment<FragmentMyDialogBinding>(){

    companion object {
        fun newInstance(
            dialogType: MyDialogType
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

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {}

    enum class MyDialogType {
        NOTIFICATION,
        MAINTENANCE,
        ABOUT,
        SAVE,
        CANCEL
    }

}