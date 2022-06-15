package app.te.lima_zola.presentation.account

import android.view.Window
import android.view.WindowManager
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentLanguageBinding
import app.te.lima_zola.presentation.auth.AuthActivity
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.events.BaseEventListener
import app.te.lima_zola.presentation.base.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageFragment : BaseFragment<FragmentLanguageBinding>(), BaseEventListener {

    private  var selectedLang: String = currentLanguage.language

    override
    fun getLayoutId() = R.layout.fragment_language
    override fun setBindingVariables() {
        binding.eventListener = this
    }

    override fun setUpViews() {
        binding.langGroup.check(if (currentLanguage.language == "ar") R.id.radio_ar else R.id.radio_en)

        binding.langGroup.setOnCheckedChangeListener { _, i ->
            selectedLang = if (i == R.id.radio_ar)
                "ar"
            else
                "en"
        }

        binding.confirmButton.setOnClickListener {
            setLanguage(selectedLang)
            openActivityAndClearStack(AuthActivity::class.java)
        }

    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.colorPrimary)
    }

    override fun back() {
        backToPreviousScreen()
    }

}