package app.te.lima_zola.presentation.auth.subscribe.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import app.te.lima_zola.R
import app.te.lima_zola.databinding.SubscribeWarningDialogBinding
import app.te.lima_zola.presentation.auth.AuthActivity
import app.te.lima_zola.presentation.base.extensions.navigateSafe
import app.te.lima_zola.presentation.base.extensions.openIntentActivity
import app.te.lima_zola.presentation.base.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubscribeWarningDialog : DialogFragment() {
    lateinit var binding: SubscribeWarningDialogBinding
    private lateinit var args: SubscribeWarningDialogArgs
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.subscribe_warning_dialog, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        args = SubscribeWarningDialogArgs.fromBundle(requireArguments())
        setupObservable()
        return binding.root
    }

    private fun setupObservable() {
        binding.agree.setOnClickListener {
            if (args.direction == Constants.LOGIN)
                openIntentActivity(AuthActivity::class.java, R.id.logInFragment)
            else
                navigateSafe(SubscribeWarningDialogDirections.actionVideosFragmentToNavSubscribe())
        }
    }

}