package app.te.lima_zola.presentation.auth.subscribe

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentPayOnlineBinding
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.openActivityAndClearStack
import app.te.lima_zola.presentation.base.extensions.showError
import app.te.lima_zola.presentation.base.utils.Constants
import app.te.lima_zola.presentation.base.utils.showSuccessAlert
import app.te.lima_zola.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import im.delight.android.webview.AdvancedWebView
import kotlinx.coroutines.delay

@AndroidEntryPoint
class PayOnlineFragment : BaseFragment<FragmentPayOnlineBinding>(), AdvancedWebView.Listener {
    private val args: PayOnlineFragmentArgs by navArgs()

    override
    fun getLayoutId() = R.layout.fragment_pay_online

    override
    fun setBindingVariables() {
        binding.webview.setListener(requireActivity(), this)
        binding.webview.setMixedContentAllowed(false)
        binding.webview.setDesktopMode(true)
        binding.webview.settings.setSupportMultipleWindows(true)
        binding.webview.loadUrl(args.paymentUrl)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        binding.webview.onActivityResult(requestCode, resultCode, intent)
    }


    override fun onPageStarted(url: String?, favicon: Bitmap?) {
        binding.webview.visibility = View.VISIBLE
    }

    private fun showSuccessDialog() {
        showSuccessAlert(requireActivity(), getString(R.string.payment_success_visa))
        lifecycleScope.launchWhenStarted {
            delay(1000)
            openActivityAndClearStack(HomeActivity::class.java)
        }
    }

    override fun onPageFinished(url: String) {
        if (url.startsWith(Constants.PAYMENT_SUCCESS)) {
            showSuccessDialog()
        }
        binding.webProgress.visibility = View.GONE
    }

    override fun onPageError(errorCode: Int, description: String, failingUrl: String?) {
        showError(description)
    }

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) {
    }

    override fun onExternalPageRequest(url: String?) {}
}