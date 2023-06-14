package com.wahidabd.siketan.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.siketan.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initUI() {}

    override fun initAction() {
        with(binding) {
            imgGrid.onClick {
                gridContainer.visible()
                listContainer.gone()
            }
            imgList.onClick {
                gridContainer.gone()
                listContainer.visible()
            }

            cardData.onClick {  }
            cardAnnouncement.onClick {  }
            cardForm.onClick {  }
            cardStore.onClick {  }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}