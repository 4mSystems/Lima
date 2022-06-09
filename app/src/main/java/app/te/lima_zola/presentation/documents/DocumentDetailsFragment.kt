package app.te.lima_zola.presentation.documents

import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentDocumentDetailsBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.events.BaseEventListener
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.presentation.documents.ui_state.DocumentDetailsUiState
import app.te.lima_zola.presentation.documents.viewModels.DocumentDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DocumentDetailsFragment : BaseFragment<FragmentDocumentDetailsBinding>(), BaseEventListener {
    private val viewModel: DocumentDetailsViewModel by viewModels()

    override
    fun getLayoutId() = R.layout.fragment_document_details
    override fun setBindingVariables() {
        binding.eventListener = this
    }

    override
    fun setupObservers() {

        lifecycleScope.launchWhenResumed {
            viewModel.articleDetailsResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        binding.contentLoading.shimmerFrameLayout.visibility =
                            View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.contentLoading.shimmerFrameLayout.visibility =
                            View.GONE
                        val uiStat = DocumentDetailsUiState(it.value.data)
                        binding.uiState = uiStat
                    }
                    is Resource.Failure -> {
                        binding.contentLoading.shimmerFrameLayout.visibility =
                            View.GONE
                        handleApiError(it)
                    }
                }
            }
        }
    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.details_status_bar)
    }

    override fun back() {
        backToPreviousScreen()
    }


}