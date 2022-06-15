package app.te.lima_zola.domain.auth.entity.model

import app.te.lima_zola.domain.auth.entity.model.payments.PaymentMethods
import app.te.lima_zola.domain.auth.entity.model.subscriptions.SubscriptionsTypes
import com.google.gson.annotations.SerializedName

class SubscriptionPaymentData {

    @SerializedName("subscription_types")
    val subscriptionTypes: List<SubscriptionsTypes> = listOf()

    @SerializedName("payment_methods")
    val paymentMethods: PaymentMethods = PaymentMethods()

}