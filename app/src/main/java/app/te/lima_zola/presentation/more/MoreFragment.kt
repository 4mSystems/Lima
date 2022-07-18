package app.te.lima_zola.presentation.more

import android.view.Window
import android.view.WindowManager
import app.te.lima_zola.BuildConfig
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentMoreBinding
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreFragment : BaseFragment<FragmentMoreBinding>(), MoreEventListener {

    override
    fun getLayoutId() = R.layout.fragment_more
    override fun setBindingVariables() {
        updateVersionName()
        binding.eventListener = this
    }

    private fun updateVersionName() {
        binding.versionName.text =
            getMyString(R.string.version_name).plus(" ( ").plus(BuildConfig.VERSION_NAME).plus(" )")
    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.account_status_bar)
    }

    override fun openTeam() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToTeamFragment())
    }

    override fun openAbout() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToAboutFragment())
    }

    override fun openAbout80Fekra() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToAboutFekraFragment())
    }

    override fun openPrivacyPolicy() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToPrivacyFragment())
    }

    override fun openTerms() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToTermsFragment())
    }

    override fun openContact() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToContactFragment())
    }

    override fun openTesDialog() {
        navigateSafe(MoreFragmentDirections.actionMoreFragmentToNavTes())
    }
}