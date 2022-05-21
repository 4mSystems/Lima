package app.te.lima_zola.presentation.auth.confirmCode

import app.te.lima_zola.presentation.base.events.BaseEventListener


interface ConfirmCodeEventListener :BaseEventListener {
  fun checkCode()
}