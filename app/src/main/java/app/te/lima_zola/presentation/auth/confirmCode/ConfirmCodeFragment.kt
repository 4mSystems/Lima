package app.te.lima_zola.presentation.auth.confirmCode

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentConfirmCodeBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ConfirmCodeFragment : BaseFragment<FragmentConfirmCodeBinding>(), ConfirmCodeEventListener {

  private val viewModel: ConfirmViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_confirm_code

  override
  fun setBindingVariables() {
    binding.request = viewModel.request
    binding.eventListener = this
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.verifyResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            openHome()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.verifyAccount() })
          }
          Resource.Default -> {
          }
        }
      }
    }

  }

  private fun openHome() {
    navigateSafe(ConfirmCodeFragmentDirections.actionConfirmCodeFragmentToNavSubscribe())
  }


  override fun checkCode() {
    viewModel.verifyAccount()
  }

  override fun back() {
    backToPreviousScreen()
  }

  override fun setupStatusBar() {
    val window: Window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = getMyColor(R.color.colorPrimary)
  }
}