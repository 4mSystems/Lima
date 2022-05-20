package app.te.lima_zola.presentation.suggestions

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.suggestions.viewModels.SuggestionsViewModel
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentSuggestionsBinding
import app.te.lima_zola.presentation.base.utils.showSuccessAlert
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuggestionsFragment : BaseFragment<FragmentSuggestionsBinding>(), EventListeners {

  private val viewModel: SuggestionsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_suggestions
  override fun setBindingVariables() {
    binding.eventListener = this
    binding.contactRequest = viewModel.contactRequest
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.suggestions.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            showSuccessAlert(requireActivity(), it.value.message)
            backToPreviousScreen()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

  }

  override fun sendContact() {
    viewModel.sendSuggestions()
  }
}