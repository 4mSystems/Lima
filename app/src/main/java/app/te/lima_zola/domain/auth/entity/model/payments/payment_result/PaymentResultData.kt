package app.te.lima_zola.domain.auth.entity.model.payments.payment_result


import com.google.gson.annotations.SerializedName

data class PaymentResultData(
    @SerializedName("invoice_id")
    val invoiceId: Int = 0,
    @SerializedName("invoice_key")
    val invoiceKey: String = "",
    @SerializedName("payment_data")
    val paymentData: PaymentData = PaymentData()
)