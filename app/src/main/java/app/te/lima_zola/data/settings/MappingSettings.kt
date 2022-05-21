package app.te.lima_zola.data.settings

import app.te.lima_zola.domain.settings.models.AboutData
import app.te.lima_zola.presentation.settings.AboutDataUiState

fun AboutData.mapToUiState(): AboutDataUiState {
  return AboutDataUiState(
    title = this.title,
    image = this.image
  )
}