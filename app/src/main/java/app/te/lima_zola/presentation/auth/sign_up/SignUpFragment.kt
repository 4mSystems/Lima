package app.te.lima_zola.presentation.auth.sign_up

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentSignUpBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(), RegisterEventListener {

  private val viewModel: SignUpViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_sign_up

  override
  fun setBindingVariables() {
    binding.request = viewModel.registerRequest
    binding.eventListener = this
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.registerResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            openConfirmCode()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.register() })
          }

        }
      }
    }
  }

  private fun openConfirmCode() {
    navigateSafe(
      SignUpFragmentDirections.actionSignUpFragmentToConfirmCodeFragment(viewModel.registerRequest)
    )
  }

  override fun signUp() {
    viewModel.register()
    navigateSafe(
      SignUpFragmentDirections.actionSignUpFragmentToConfirmCodeFragment(viewModel.registerRequest)
    )
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