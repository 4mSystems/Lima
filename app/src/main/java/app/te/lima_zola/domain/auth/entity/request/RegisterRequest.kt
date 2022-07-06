package app.te.lima_zola.domain.auth.entity.request

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.databinding.ObservableField
import app.te.lima_zola.domain.utils.BaseRequest
import app.te.lima_zola.presentation.base.utils.Constants
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class RegisterRequest : BaseRequest(), Parcelable {

    var name: String = ""
        set(value) {
            validation.nameError.set(null)
            field = value
        }
    var phone: String = ""
        set(value) {
            validation.phoneError.set(null)
            field = value
        }
    var city_id: String = ""
        set(value) {
            validation.cityError.set(null)
            field = value
        }
    var password: String = ""
        set(value) {
            validation.passwordError.set(null)
            field = value
        }
    var otp: String = ""


    @Transient
    var validation: RegisterValidationException = RegisterValidationException()

}

@Keep
class RegisterValidationException {
    var nameError: ObservableField<String> = ObservableField<String>()
    var phoneError: ObservableField<String> = ObservableField<String>()
    var passwordError: ObservableField<String> = ObservableField<String>()
    var cityError: ObservableField<String> = ObservableField<String>()


}
