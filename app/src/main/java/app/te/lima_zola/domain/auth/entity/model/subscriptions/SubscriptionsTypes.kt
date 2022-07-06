package app.te.lima_zola.domain.auth.entity.model.subscriptions


import com.google.gson.annotations.SerializedName

data class SubscriptionsTypes(
    @SerializedName("cost")
    val cost: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("month_count")
    val monthCount: String = "",
    @SerializedName("name")
    val name: String = "",
    var isSelected: Boolean = false
) {
    fun title(): String = name.plus(" ( ").plus(cost).plus(" )")
}