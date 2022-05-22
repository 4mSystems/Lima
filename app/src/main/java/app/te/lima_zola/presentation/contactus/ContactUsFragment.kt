package app.te.lima_zola.presentation.contactus

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.R
import app.te.lima_zola.data.settings.mapToUiState
import app.te.lima_zola.databinding.FragmentContactUsBinding
import app.te.lima_zola.presentation.base.events.BaseEventListener
import app.te.lima_zola.presentation.settings.viewModels.SettingsViewModel
import app.te.lima_zola.presentation.base.utils.openBrowser
import dagger.hilt.android.AndroidEntryPoint
import app.te.lima_zola.presentation.base.utils.openWhatsApp
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ContactUsFragment : BaseFragment<FragmentContactUsBinding>(), BaseEventListener {
  private val viewModel: SettingsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_contact_us
  override fun setBindingVariables() {
    binding.eventListener = this
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.aboutResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            binding.uiState = it.value.data.mapToUiState()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

  }

  override fun setupStatusBar() {
    val window: Window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = getMyColor(R.color.colorPrimary)
  }

  override fun back() {
    backToPreviousScreen()
  }
}