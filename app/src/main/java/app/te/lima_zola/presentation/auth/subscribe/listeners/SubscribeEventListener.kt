package app.te.lima_zola.presentation.auth.subscribe.listeners

import app.te.lima_zola.presentation.base.events.BaseEventListener


interface SubscribeEventListener : BaseEventListener {
    fun subscribe()
}