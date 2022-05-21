package app.te.lima_zola.domain.auth.entity.request

import androidx.annotation.Keep
import androidx.databinding.ObservableField

@Keep
class LogInRequest {
  var phone: String = ""
    set(value) {
      validation.emailError.set(null)
      field = value
    }
  var password: String = ""
    set(value) {
      validation.passwordError.set(null)
      field = value
    }
  var validation: LogInValidationException = LogInValidationException()
}

@Keep
class LogInValidationException {
  var emailError: ObservableField<String> = ObservableField<String>()
  var passwordError: ObservableField<String> = ObservableField<String>()

}
