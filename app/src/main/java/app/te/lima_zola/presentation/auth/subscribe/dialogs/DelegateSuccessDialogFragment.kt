package app.te.lima_zola.presentation.auth.subscribe.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.te.lima_zola.R
import app.te.lima_zola.databinding.DelegateSuccesDialogBinding
import app.te.lima_zola.presentation.base.extensions.openActivityAndClearStack
import app.te.lima_zola.presentation.home.HomeActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DelegateSuccessDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: DelegateSuccesDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.delegate_succes_dialog, container, false)
        isCancelable = false
        binding.btnBrowse.setOnClickListener {
            openActivityAndClearStack(HomeActivity::class.java)
        }
        return binding.root
    }

}