package com.wahidabd.siketan.presentation.store

import android.view.LayoutInflater
import android.view.ViewGroup
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.siketan.databinding.FragmentStoreAddBinding

class StoreAddFragment : BaseFragment<FragmentStoreAddBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentStoreAddBinding = FragmentStoreAddBinding.inflate(layoutInflater)

    override fun initProcess() {}

    override fun initAction() {
        binding.img.onClick {
            launcherPicker.launch(
                ImagePickerConfig(
                    ImagePickerMode.SINGLE,
                    returnMode = ReturnMode.ALL
                )
            )
        }
    }

    override fun initUI() {}

    override fun initObservers() {}

    private val launcherPicker = registerImagePicker {
        with(binding) {
            it.forEach { image ->
                img.setImageURI(image.uri)
                debug { "$image" }
                debug { "${image.uri}" }
                debug { image.path }
                debug { image.name }
            }
        }
    }

}