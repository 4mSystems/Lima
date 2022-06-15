package app.te.lima_zola.domain.auth.entity.model.payments.payment_result


import com.google.gson.annotations.SerializedName

data class PaymentData(
    @SerializedName("redirectTo")
    val redirectTo: String = ""
)