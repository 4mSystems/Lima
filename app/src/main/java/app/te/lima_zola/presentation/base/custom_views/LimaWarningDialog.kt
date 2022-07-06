package app.te.lima_zola.presentation.base.custom_views

import androidx.navigation.fragment.navArgs
import app.te.lima_zola.R
import app.te.lima_zola.databinding.LimaWarningDialogBinding
import app.te.lima_zola.presentation.base.BaseDialog
import app.te.lima_zola.presentation.base.extensions.navigateSafe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LimaWarningDialog : BaseDialog<LimaWarningDialogBinding>() {
    private val args: LimaWarningDialogArgs by navArgs()
    override fun setUpViews() {
        dialog?.setCancelable(false)
        binding.agree.setOnClickListener {
            dismiss()
            navigateSafe(
                LimaWarningDialogDirections.actionLimaWarningDialogToVideosFragment(
                    args.param
                )
            )
        }
        binding.cancelAction.setOnClickListener {
            dismiss()
        }
    }

    override fun getLayoutId(): Int = R.layout.lima_warning_dialog

}