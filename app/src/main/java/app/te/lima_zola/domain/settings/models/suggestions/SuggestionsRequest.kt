package app.te.lima_zola.domain.settings.models.suggestions

import androidx.annotation.Keep
import androidx.databinding.ObservableField

@Keep
class SuggestionsRequest {
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
  var address: String = ""
    set(value) {
      validation.addressError.set(null)
      field = value
    }
  var message: String = ""
    set(value) {
      validation.messageError.set(null)
      field = value
    }
  var validation: ContactValidationException = ContactValidationException()
}

@Keep
class ContactValidationException {
  var nameError: ObservableField<String> = ObservableField<String>()
  var phoneError: ObservableField<String> = ObservableField<String>()
  var addressError: ObservableField<String> = ObservableField<String>()
  var messageError: ObservableField<String> = ObservableField<String>()

}
