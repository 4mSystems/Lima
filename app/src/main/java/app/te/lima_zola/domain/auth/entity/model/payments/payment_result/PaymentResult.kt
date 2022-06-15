package app.te.lima_zola.domain.auth.entity.model.payments.payment_result


import com.google.gson.annotations.SerializedName

data class PaymentResult(
    @SerializedName("data")
    val paymentResultData: PaymentResultData = PaymentResultData(),
    @SerializedName("status")
    val status: String = ""
)