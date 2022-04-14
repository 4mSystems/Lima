package app.te.protein_chef.presentation.order_details.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.CancelOrderRequest
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.FreezeOrderRequest
import app.te.protein_chef.domain.my_orders.use_case.CancelOrderUseCase
import app.te.protein_chef.domain.my_orders.use_case.FreezeOrderUseCase
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import app.te.protein_chef.presentation.order_details.dialog.CancelOrderBankDataDialogArgs
import app.te.protein_chef.presentation.order_details.dialog.FreezeOrderDialogArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FreezeOrderViewModel @Inject constructor(
  private val freezeOrderUseCase: FreezeOrderUseCase,
  val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

  private val _feeezeOrderResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val feeezeOrderResponse = _feeezeOrderResponse

  fun freezeOrder(freezeOrderRequest: FreezeOrderRequest) {
    freezeOrderRequest.order_id =
      FreezeOrderDialogArgs.fromSavedStateHandle(savedStateHandle).orderId
    viewModelScope.launch {
      freezeOrderUseCase.invoke(freezeOrderRequest)
        .collect { result ->
          _feeezeOrderResponse.value = result
        }
    }
  }

}