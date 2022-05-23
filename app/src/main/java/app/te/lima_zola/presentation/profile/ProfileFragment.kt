package app.te.lima_zola.presentation.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentProfileBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.presentation.base.utils.showSuccessAlert
import app.te.lima_zola.presentation.home.HomeActivity
import app.te.lima_zola.presentation.profile.viewModels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(), ProfileEventListener {

  private val viewModel: ProfileViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_profile

  override
  fun setBindingVariables() {
    binding.eventListener = this
    binding.request = viewModel.request
  }


  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.profileResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            openHome(it.value.message)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(
              it,
              retryAction = { viewModel.updateProfile() })
          }

        }
      }
    }
  }

  private fun openHome(message: String) {
    lifecycleScope.launch {
      delay(1000)
      showSuccessAlert(requireActivity(), message)
    }
    openActivityAndClearStack(HomeActivity::class.java)
  }

  override fun updateProfile() {
    viewModel.updateProfile()
  }

  override fun changePassword() {
    navigateSafe(ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment())
  }

  override fun back() {
    backToPreviousScreen()
  }

}