package app.te.lima_zola.presentation.account

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentAccountBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.auth.AuthActivity
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import codes.grand.pretty_pop_up.PrettyPopUpHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>(), AccountEventListener {
  private val accountViewModel: AccountViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_account
  override fun setBindingVariables() {
    binding.eventListener = this
  }

  override
  fun setupObservers() {

    lifecycleScope.launchWhenResumed {
      accountViewModel.logOutResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            accountViewModel.clearStorage()
            openLogInScreen()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
  }

  private fun showLogOutPopUp() {
    PrettyPopUpHelper.Builder(childFragmentManager)
      .setStyle(PrettyPopUpHelper.Style.STYLE_1_HORIZONTAL_BUTTONS)
      .setTitle(R.string.log_out)
      .setTitleColor(getMyColor(R.color.colorPrimaryDark))
      .setContent(R.string.log_out_hint)
      .setContentColor(getMyColor(R.color.colorGray))
      .setPositiveButtonBackground(R.color.colorPrimaryDark)
      .setPositiveButtonTextColor(getMyColor(R.color.white))
      .setImage(R.drawable.ic_logout)
      .setPositiveButton(R.string.log_out) {
        it.dismiss()
        accountViewModel.logOut()
      }
      .setNegativeButtonBackground(R.drawable.corner_view_gray_border)
      .setNegativeButtonTextColor(getMyColor(R.color.white))
      .setNegativeButton(getMyString(R.string.cancel), null)
      .create()
  }

  private fun openLogInScreen() {
    requireActivity().openActivityAndClearStack(AuthActivity::class.java)
  }

  override fun setupStatusBar() {
    val window: Window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = getMyColor(R.color.account_status_bar)
  }

  override fun openProfile() {
    TODO("Not yet implemented")
  }

  override fun openFavorite() {
    TODO("Not yet implemented")
  }

  override fun openSubscribe() {
    TODO("Not yet implemented")
  }

  override fun openChangeLanguage() {
    TODO("Not yet implemented")
  }

  override fun logout() {
    TODO("Not yet implemented")
  }
}