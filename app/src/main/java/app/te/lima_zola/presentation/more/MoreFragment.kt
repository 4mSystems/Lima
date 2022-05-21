package app.te.lima_zola.presentation.more

import android.view.Window
import android.view.WindowManager
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
    binding.eventListener = this
  }

  override fun setupStatusBar() {
    val window: Window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = getMyColor(R.color.account_status_bar)
  }

  override fun openTeam() {
    TODO("Not yet implemented")
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
    TODO("Not yet implemented")
  }
}