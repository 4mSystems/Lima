package app.te.lima_zola.presentation.contactus

import app.te.lima_zola.presentation.base.events.BaseEventListener

interface ContactUsEventListeners : BaseEventListener {
  fun openContactUrl(url: String)
}