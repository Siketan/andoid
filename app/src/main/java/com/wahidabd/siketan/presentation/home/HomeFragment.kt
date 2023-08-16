package com.wahidabd.siketan.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.siketan.databinding.FragmentHomeBinding
import com.wahidabd.siketan.presentation.auth.AuthActivity
import com.wahidabd.siketan.presentation.chat.ChatActivity
import com.wahidabd.siketan.utils.PrefManager
import com.wahidabd.siketan.utils.components.MyDialogFragment
import com.wahidabd.siketan.utils.navigate
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val prefs: PrefManager by inject()
    private var reveal = true

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
            fabChat.onClick { ChatActivity.start(requireContext()) }
            imgGrid.onClick {
                gridContainer.visible()
                listContainer.gone()
            }
            imgList.onClick {
                gridContainer.gone()
                listContainer.visible()
            }

            cardData.onClick { navigate(HomeFragmentDirections.actionHomeFragmentToDataFormerFragment()) }
            cardAnnouncement.onClick { navigate(HomeFragmentDirections.actionHomeFragmentToAnnouncementFragment()) }
            cardForm.onClick { navigate(HomeFragmentDirections.actionHomeFragmentToJournalFragment()) }
            cardStore.onClick { navigate(HomeFragmentDirections.actionHomeFragmentToStoreFragment()) }
            listData.onClick { navigate(HomeFragmentDirections.actionHomeFragmentToDataFormerFragment()) }
            listInfo.onClick { navigate(HomeFragmentDirections.actionHomeFragmentToAnnouncementFragment()) }
            listForm.onClick { navigate(HomeFragmentDirections.actionHomeFragmentToJournalFragment()) }
            listStore.onClick { navigate(HomeFragmentDirections.actionHomeFragmentToStoreFragment()) }


            // floating menu
            fabMenu.onClick {
                fabMenu.isExpanded = reveal
                reveal = !reveal
            }

            imgAbout.onClick {
                MyDialogFragment.newInstance(MyDialogFragment.MyDialogType.ABOUT)
                    .show(parentFragmentManager, MyDialogFragment::class.java.name)
            }

            imgUser.onClick { navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment()) }

            imgLogout.onClick {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Keluar")
                    .setMessage("Apakah anda yakin ingin keluar dari aplikasi?")
                    .setPositiveButton("YA"){dialog, _ ->
                        prefs.logout()
                        AuthActivity.start(requireContext())
                        requireActivity().finish()
                    }
                    .setNegativeButton("BATAL"){dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}