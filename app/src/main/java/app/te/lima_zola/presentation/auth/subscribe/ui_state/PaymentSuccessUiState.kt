package app.te.lima_zola.presentation.auth.subscribe.ui_state

import android.content.Context
import android.view.View
import app.te.lima_zola.R
import app.te.lima_zola.domain.auth.entity.model.payments.payment_result.PaymentData

class PaymentSuccessUiState(val paymentItem: PaymentData) {

    fun successMessage(context: Context): String =
        if (paymentItem.fawryCode.isNotEmpty())
            context.getString(R.string.payment_fawary_code)
        else if (paymentItem.amanCode.isNotEmpty())
            context.getString(R.string.payment_aman_code)
        else if (paymentItem.meezaReference.isNotEmpty())
            context.getString(R.string.payment_wallet)
        else ""


    fun paymentCode(): String =
        paymentItem.fawryCode.ifEmpty { paymentItem.meezaReference.ifEmpty { paymentItem.amanCode.ifEmpty { "" } } }

    fun expireAtVisibility(): Int =
        if (paymentItem.expireDate.isNotEmpty()) View.VISIBLE else View.GONE
}