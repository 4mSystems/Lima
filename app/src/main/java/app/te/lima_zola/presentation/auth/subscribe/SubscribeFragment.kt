package app.te.lima_zola.presentation.auth.subscribe

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentSubscribeBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SubscribeFragment : BaseFragment<FragmentSubscribeBinding>(), SubscribeEventListener {

  private val viewModel: SubscribeViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_subscribe

  override
  fun setBindingVariables() {
    binding.uiState = viewModel.uiState
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
  }

  override fun subscribe() {
    openActivityAndClearStack(HomeActivity::class.java)
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