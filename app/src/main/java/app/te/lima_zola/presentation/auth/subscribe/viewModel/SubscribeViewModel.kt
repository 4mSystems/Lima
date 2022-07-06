package app.te.lima_zola.presentation.auth.subscribe.viewModel

import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.auth.entity.model.SubscriptionPaymentData
import app.te.lima_zola.domain.auth.entity.model.payments.payment_result.PaymentResult
import app.te.lima_zola.domain.auth.use_case.GetPaymentResultUseCase
import app.te.lima_zola.domain.auth.use_case.GetSubscriptionTypesUseCase
import app.te.lima_zola.domain.auth.use_case.PayWithDelegateUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SubscribeViewModel @Inject constructor(
    private val subscriptionTypesUseCase: GetSubscriptionTypesUseCase,
    private val paymentResultUseCase: GetPaymentResultUseCase,
    private val payWithDelegateUseCase: PayWithDelegateUseCase
) :
    BaseViewModel() {
    private val _subscriptionResponse =
        MutableStateFlow<Resource<BaseResponse<SubscriptionPaymentData>>>(Resource.Default)
    val subscriptionResponse = _subscriptionResponse
    private val _paymentResponse =
        MutableStateFlow<Resource<BaseResponse<PaymentResult>>>(Resource.Default)
    val paymentResponse = _paymentResponse

    private val _payDelegateResponse =
        MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
    val payDelegateResponse = _payDelegateResponse

    init {
        getSubscriptionTypes()
    }

    fun getSubscriptionTypes() {
        subscriptionTypesUseCase()
            .onEach { result ->
                _subscriptionResponse.value = result
            }
            .launchIn(viewModelScope)
    }

    fun getPaymentResult(
        subscribe_id: Int,
        payment_id: Int
    ) {
        paymentResultUseCase(subscribe_id, payment_id)
            .onEach { result ->
                _paymentResponse.value = result
            }
            .launchIn(viewModelScope)
    }

    fun callDelegatePayment(
        subscribe_id: Int
    ) {
        payWithDelegateUseCase(subscribe_id)
            .onEach { result ->
                _payDelegateResponse.value = result
            }
            .launchIn(viewModelScope)
    }

}