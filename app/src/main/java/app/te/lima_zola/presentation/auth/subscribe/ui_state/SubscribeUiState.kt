package app.te.lima_zola.presentation.auth.subscribe.ui_state

import androidx.databinding.Bindable
import app.te.lima_zola.BR
import app.te.lima_zola.domain.auth.entity.model.subscriptions.SubscriptionsTypes
import app.te.lima_zola.domain.auth.entity.request.ActivatePromoCodeRequest
import app.te.lima_zola.presentation.base.BaseUiState

class SubscribeUiState : BaseUiState() {
    @Bindable
    var subscriptionsTypeValue: Float = 0F

    @Bindable
    var discountValue: Float = 0F

    @Bindable
    var totalValue: Float = 0F

    val activatePromoCodeRequest = ActivatePromoCodeRequest()

    private var subscriptionsTypes = SubscriptionsTypes()

    fun updateUI(subscriptionsTypes: SubscriptionsTypes) {
        this.subscriptionsTypes = subscriptionsTypes
        // update ui of @+id/tv_subscribe_cost_value
        updateSubscriptionType(subscriptionsTypes.cost)

        updateDiscountValue(discountValue)
        // update promo code request with subscribe type id
        activatePromoCodeRequest.subscribe_type_id = subscriptionsTypes.id
    }

    private fun updateTotalValue() {
        // update ui of @+id/tv_total_cost_value
        totalValue = subscriptionsTypeValue - discountValue
        notifyPropertyChanged(BR.totalValue)
    }

    private fun updateSubscriptionType(cost: String) {
        subscriptionsTypeValue = cost.toFloat()
        notifyPropertyChanged(BR.subscriptionsTypeValue)
    }

    fun updateDiscountValue(value: Float) {
        // update ui of @+id/tv_discount_cost_value
        discountValue = value
        notifyPropertyChanged(BR.discountValue)
        updateTotalValue()
    }
}