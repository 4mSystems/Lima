package app.te.lima_zola.presentation.auth.subscribe

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import androidx.navigation.fragment.navArgs
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentPayOnlineBinding
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.showError
import dagger.hilt.android.AndroidEntryPoint
import im.delight.android.webview.AdvancedWebView

@AndroidEntryPoint
class PayOnlineFragment : BaseFragment<FragmentPayOnlineBinding>(), AdvancedWebView.Listener {
    val args: PayOnlineFragmentArgs by navArgs()

    override
    fun getLayoutId() = R.layout.fragment_pay_online

    override
    fun setBindingVariables() {
        binding.webview.setListener(requireActivity(), this)
        binding.webview.setMixedContentAllowed(false)
        binding.webview.setDesktopMode(true)
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
//        val dialog = Dialog(requireActivity(), R.style.PauseDialog)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        Objects.requireNonNull(dialog.window).attributes.windowAnimations =
//            R.style.PauseDialogAnimation
//        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        val binding: SuccessDialogBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(dialog.context),
//            R.layout.success_dialog,
//            null,
//            false
//        )
//        dialog.setContentView(binding.getRoot())
//        dialog.setOnDismissListener { dialog1: DialogInterface? ->
//            dialog.dismiss()
//            finish()
//        }
//        dialog.show()
    }

    override fun onPageFinished(url: String) {
//        https://aljoud-edu.com/api/pay/error?invoice_id=1340340
        if (url.startsWith("https://fawaterkstage.com/nbe/success")) {
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