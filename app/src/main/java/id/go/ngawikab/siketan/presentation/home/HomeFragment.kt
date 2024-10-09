package id.go.ngawikab.siketan.presentation.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wahidabd.library.presentation.fragment.BaseFragment
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import com.wahidabd.library.utils.exts.visibleIf
import id.go.ngawikab.siketan.databinding.FragmentHomeBinding
import id.go.ngawikab.siketan.presentation.auth.AuthActivity
import id.go.ngawikab.siketan.utils.PrefManager
import id.go.ngawikab.siketan.utils.UserRole
import id.go.ngawikab.siketan.utils.components.MyDialogFragment
import id.go.ngawikab.siketan.utils.formatPhoneNumber
import id.go.ngawikab.siketan.utils.navigate
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val prefs: PrefManager by inject()
    private var reveal = true

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun initUI() {

    }

    override fun initAction() {
        val user = prefs.getUser()
        with(binding) {
            fabChat.onClick {
                if (user.role == UserRole.PETANI.role) {
                    val contactNumber = formatPhoneNumber(user.no_penyuluh?.trim())
                    val message = "Hi, Bapak/Ibu "+user.nama_penyuluh+", saya "+user.nama+" keperluan saya menghubungi ialah ..."
                    try{
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("whatsapp://send?phone=${contactNumber}&text=${Uri.encode(message)}")
                        context?.startActivity(intent)
                    }catch (e:Exception){
                        showToast("Whatsapp wasn't installed!")
                    }
                }
                else if (user.role == UserRole.PENYULUH.role){
                    navigate(HomeFragmentDirections.actionHomeFragmentToChatFragment())
                }
            }
            imgGrid.onClick {
                gridContainer.visible()
                listContainer.gone()
            }
            imgList.onClick {
                gridContainer.gone()
                listContainer.visible()
            }
            cardForm.gone()
            listForm.gone()
            cardForm.visibleIf { user.role == UserRole.PENYULUH.role }
            listForm.visibleIf { user.role == UserRole.PENYULUH.role }
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

    override fun initProcess() {

    }
    override fun initObservers() {

    }
}