package app.te.lima_zola.domain.auth.entity.model.payments


import com.google.gson.annotations.SerializedName

data class PaymentMethods(
    @SerializedName("data")
    val paymentItem: List<PaymentItem> = listOf(),
    @SerializedName("status")
    val status: String = ""
)