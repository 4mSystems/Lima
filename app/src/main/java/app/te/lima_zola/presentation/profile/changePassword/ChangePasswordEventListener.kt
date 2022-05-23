package app.te.lima_zola.presentation.profile.changePassword

import app.te.lima_zola.presentation.base.events.BaseEventListener

interface ChangePasswordEventListener :BaseEventListener{
  fun changePassword()
}