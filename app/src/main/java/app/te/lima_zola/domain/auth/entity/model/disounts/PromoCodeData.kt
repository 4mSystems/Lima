package app.te.lima_zola.domain.auth.entity.model.disounts


import com.google.gson.annotations.SerializedName

data class PromoCodeData(
    @SerializedName("discount")
    val discount: Float = 0F,
    @SerializedName("discount_percentage")
    val discountPercentage: Int = 0,
    @SerializedName("finalTotal")
    val finalTotal: Int = 0,
    @SerializedName("totalOrder")
    val totalOrder: Int = 0
)