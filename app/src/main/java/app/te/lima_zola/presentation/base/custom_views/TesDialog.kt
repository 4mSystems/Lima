package app.te.lima_zola.presentation.base.custom_views

import android.view.Window
import app.te.lima_zola.R
import app.te.lima_zola.databinding.LayoutTesBinding
import app.te.lima_zola.presentation.base.BaseDialog
import app.te.lima_zola.presentation.base.extensions.getMyDrawable
import app.te.lima_zola.presentation.base.utils.openBrowser

class TesDialog : BaseDialog<LayoutTesBinding>() {
    override fun getLayoutId(): Int = R.layout.layout_tes
    override fun setUpViews() {
        binding.tvGrandUrl.setOnClickListener {
            openBrowser(requireActivity(), binding.tvGrandUrl.text.toString())
        }
        binding.imgGrandClose.setOnClickListener {
            dismiss()
        }
    }

    override fun updateLayoutParams() {
        val params = dialog!!.window!!.attributes
        dialog!!.window!!.attributes = params
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(getMyDrawable(R.drawable.corner_view_gray_border))

    }
}