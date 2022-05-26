package app.te.lima_zola.presentation.profile

import app.te.lima_zola.presentation.base.events.BaseEventListener

interface ProfileEventListener : BaseEventListener {
  fun updateProfile()
  fun changePassword()
  fun showCities()
}