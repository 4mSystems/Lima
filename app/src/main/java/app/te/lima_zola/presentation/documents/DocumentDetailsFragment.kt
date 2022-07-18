package app.te.lima_zola.presentation.documents

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebSettings
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
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter


@AndroidEntryPoint
class DocumentDetailsFragment : BaseFragment<FragmentDocumentDetailsBinding>(), BaseEventListener {
    private val viewModel: DocumentDetailsViewModel by viewModels()

    override
    fun getLayoutId() = R.layout.fragment_document_details
    override fun setBindingVariables() {
        binding.eventListener = this
        binding.webview.setMixedContentAllowed(false)
        binding.webview.setDesktopMode(false)
//        binding.webview.setBackgroundColor(R.color.transparent)
//        binding.webview.settings.loadWithOverviewMode = true
//        binding.webview.settings.useWideViewPort = true
    }

    override
    fun setupObservers() {

        lifecycleScope.launchWhenResumed {
            viewModel.articleDetailsResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        binding.contentLoading.shimmerFrameLayout.visibility =
                            View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.contentLoading.shimmerFrameLayout.visibility =
                            View.GONE
                        val uiStat = DocumentDetailsUiState(it.value.data)
                        binding.webview.loadDataWithBaseURL(
                            "",
                            uiStat.documentBody, "text/html", "utf-8", ""
                        )

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

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        binding.webview.onResume()
    }

    @SuppressLint("NewApi")
    override fun onPause() {
        binding.webview.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        binding.webview.onDestroy()
        super.onDestroy()
    }

}