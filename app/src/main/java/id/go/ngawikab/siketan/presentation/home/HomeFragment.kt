package id.go.ngawikab.siketan.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import id.go.ngawikab.siketan.databinding.FragmentHomeBinding
import id.go.ngawikab.siketan.presentation.auth.AuthActivity
import id.go.ngawikab.siketan.presentation.chat.ChatActivity
import id.go.ngawikab.siketan.presentation.chat.ChatRoomActivity
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.UserRole
import id.go.ngawikab.siketan.utils.components.MyDialogFragment
import id.go.ngawikab.siketan.utils.navigate
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

    override fun initUI() {
        debug { "User id: ${prefs.getUser().id}" }
    }

    override fun initAction() {
        with(binding) {
            fabChat.onClick {
                val user = prefs.getUser().role
                if (user == UserRole.PETANI.role) ChatRoomActivity.start(requireContext())
                else if (user == UserRole.PENYULUH.role) ChatActivity.start(requireContext())
            }
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
                    .setPositiveButton("YA") { dialog, _ ->
                        prefs.logout()
                        AuthActivity.start(requireContext())
                        requireActivity().finish()
                    }
                    .setNegativeButton("BATAL") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

}