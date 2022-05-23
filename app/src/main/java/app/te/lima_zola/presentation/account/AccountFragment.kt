package app.te.lima_zola.presentation.account

import android.content.Intent
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.NavHostFragment
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentAccountBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.auth.AuthActivity
import app.te.lima_zola.presentation.auth.log_in.LogInFragment
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import codes.grand.pretty_pop_up.PrettyPopUpHelper
import com.google.android.gms.auth.api.Auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>(), AccountEventListener {
  private val accountViewModel: AccountViewModel by viewModels()
  lateinit var uiState: AccountUiState

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
    lifecycleScope.launchWhenResumed {
      accountViewModel.userData.collect {
        uiState = AccountUiState(it)
        binding.uiState = uiState
        uiState.updateUi()
      }
    }
  }

  private fun showLogOutPopUp() {
    PrettyPopUpHelper.Builder(childFragmentManager)
      .setStyle(PrettyPopUpHelper.Style.STYLE_1_HORIZONTAL_BUTTONS)
      .setTitle(R.string.log_out)
      .setTitleColor(getMyColor(R.color.black))
      .setContent(R.string.log_out_hint)
      .setContentColor(getMyColor(R.color.black))
      .setPositiveButtonBackground(R.drawable.corner_view_primary_dark)
      .setPositiveButtonTextColor(getMyColor(R.color.white))
      .setImage(R.drawable.ic_logout)
      .setPositiveButton(R.string.log_out) {
        it.dismiss()
        accountViewModel.logOut()
      }
      .setNegativeButtonBackground(R.drawable.corner_view_gray_border)
      .setNegativeButtonTextColor(getMyColor(R.color.black))
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
    navigateSafe(AccountFragmentDirections.actionAccountFragmentToProfileFragment())
  }

  override fun openFavorite() {
    TODO("Not yet implemented")
  }

  override fun openSubscribe() {
    navigateSafe(AccountFragmentDirections.actionAccountFragmentToNavSubscribe())
  }

  override fun openChangeLanguage() {
    navigateSafe(AccountFragmentDirections.actionAccountFragmentToLanguageFragment())
  }

  override fun logout() {
    if (uiState.user.name.isNotEmpty())
      showLogOutPopUp()
    else {
      openIntentActivity(AuthActivity::class.java, R.id.logInFragment)
    }
  }
}