package app.te.lima_zola.domain.auth.entity.model.payments.payment_result


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentData(
    @SerializedName("redirectTo")
    val redirectTo: String = "",
    @SerializedName("expireDate")
    @Expose
    var expireDate: String = "",
    @SerializedName("fawryCode")
    @Expose
    val fawryCode: String = "",

    @SerializedName("amanCode")
    @Expose
    val amanCode: String = "",

    @SerializedName("meezaReference")
    @Expose
    val meezaReference: String = ""

) : Parcelable