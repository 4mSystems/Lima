package app.te.lima_zola.domain.auth.entity.request

import android.os.Parcelable
import androidx.annotation.Keep
import app.te.lima_zola.presentation.base.utils.Constants
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ForgetPasswordRequest(
    var phone: String = "",
    var code: String = "",
) : Parcelable