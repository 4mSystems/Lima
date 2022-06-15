package app.te.lima_zola.domain.auth.entity.model.payments


import com.google.gson.annotations.SerializedName
import java.util.*

data class PaymentItem(
    @SerializedName("logo")
    val logo: String = "",
    @SerializedName("name_ar")
    val nameAr: String = "",
    @SerializedName("name_en")
    val nameEn: String = "",
    @SerializedName("paymentId")
    val paymentId: Int = 0,
    @SerializedName("redirect")
    val redirect: String = "",
    var isSelected: Boolean = false

) {
    fun paymentName(): String = if (Locale.getDefault().language == "ar") nameAr else nameEn

}