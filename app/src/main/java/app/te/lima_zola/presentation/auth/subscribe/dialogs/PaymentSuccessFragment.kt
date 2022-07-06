package app.te.lima_zola.presentation.auth.subscribe.dialogs

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import app.te.lima_zola.R
import app.te.lima_zola.databinding.PaymentSuccesDialogBinding
import app.te.lima_zola.presentation.auth.subscribe.listeners.PaymentSuccessListener
import app.te.lima_zola.presentation.auth.subscribe.ui_state.PaymentSuccessUiState
import app.te.lima_zola.presentation.base.extensions.openActivityAndClearStack
import app.te.lima_zola.presentation.base.utils.copyText
import app.te.lima_zola.presentation.base.utils.showSuccessAlert
import app.te.lima_zola.presentation.home.HomeActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentSuccessFragment : BottomSheetDialogFragment(), PaymentSuccessListener {
    lateinit var binding: PaymentSuccesDialogBinding
    private val args: PaymentSuccessFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.payment_succes_dialog, container, false)
        val paymentItem = args.paymentItem
        binding.uiState = PaymentSuccessUiState(paymentItem)
        binding.eventListener = this
        isCancelable = false

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog?
            setupFullHeight(bottomSheetDialog)
        }
        return dialog
    }
    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog?) {
        val bottomSheet: FrameLayout? =
            bottomSheetDialog?.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(
            bottomSheet!!
        )
        val layoutParams: ViewGroup.LayoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        layoutParams.height = windowHeight
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
    override fun makeCopy(code: String) {
        copyText(code, requireContext())
        showSuccessAlert(requireActivity(), code.plus("\n").plus(getString(R.string.copied)))
        dismiss()
        openActivityAndClearStack(HomeActivity::class.java)
    }

}