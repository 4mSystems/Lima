package app.te.lima_zola.presentation.auth.sign_up

import app.te.lima_zola.presentation.base.events.BaseEventListener


interface RegisterEventListener : BaseEventListener {
  fun signUp()
}