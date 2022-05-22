package app.te.lima_zola.presentation.account

import android.view.Window
import android.view.WindowManager
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentLanguageBinding
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.events.BaseEventListener
import app.te.lima_zola.presentation.base.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageFragment : BaseFragment<FragmentLanguageBinding>(), BaseEventListener {

  override
  fun getLayoutId() = R.layout.fragment_language
  override fun setBindingVariables() {
    binding.eventListener = this
  }

  //TODO Change language
  override fun setupStatusBar() {
    val window: Window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = getMyColor(R.color.colorPrimary)
  }

  override fun back() {
    backToPreviousScreen()
  }

}