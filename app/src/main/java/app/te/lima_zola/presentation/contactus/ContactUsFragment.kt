package app.te.lima_zola.presentation.contactus

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentContactUsBinding
import app.te.lima_zola.domain.settings.models.ContactUs
import app.te.lima_zola.presentation.base.utils.openBrowser
import app.te.lima_zola.presentation.base.utils.openDial
import app.te.lima_zola.presentation.settings.viewModels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import app.te.lima_zola.presentation.settings.adapters.ContactUsAdapter
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ContactUsFragment : BaseFragment<FragmentContactUsBinding>(), ContactUsEventListeners {
  private val viewModel: SettingsViewModel by viewModels()
  private lateinit var contactUsAdapter: ContactUsAdapter

  override
  fun getLayoutId() = R.layout.fragment_contact_us
  override fun setBindingVariables() {
    binding.eventListener = this
    contactUsAdapter = ContactUsAdapter(this)
    viewModel.getContactLinks()
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.contactResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            setUpContact(it.value.data)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

  }

  private fun setUpContact(data: List<ContactUs>) {
    contactUsAdapter.differ.submitList(viewModel.mapToContactUiState(data))
    binding.rcContact.setUpAdapter(contactUsAdapter, "3", "1")

  }

  override fun setupStatusBar() {
    val window: Window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = getMyColor(R.color.colorPrimary)
  }

  override fun openContactUrl(url: String) {
    if (url.isNumeric(url))
      openDial(requireContext(), url)
    else
      openBrowser(requireContext(), url)
  }

  override fun back() {
    backToPreviousScreen()
  }
}