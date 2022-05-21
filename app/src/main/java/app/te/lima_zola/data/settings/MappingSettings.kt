package app.te.lima_zola.data.settings

import app.te.lima_zola.domain.settings.models.AboutData
import app.te.lima_zola.presentation.about.AboutDataUiState

fun AboutData.mapToUiState(): AboutDataUiState {
  return AboutDataUiState(
    whatsapp = this.whatsapp,
    monkezRecieve = this.monkezRecieve,
    monkezName = this.monkezName,
    facebook = this.facebook,
    youtube = this.youtube,
    body = this.body,
    monkezPreReserve = this.monkezPreReserve,
    twitter = this.twitter,
    phone = this.phone,
    monkezSystem = this.monkezSystem,
    email = this.email,
    monkezPhone = this.monkezPhone,
  )
}