package app.grand.tafwak.domain.my_coupons.use_case

import app.grand.tafwak.domain.my_coupons.repository.MyCouponsRepository
import app.grand.tafwak.domain.utils.*
import app.grand.tafwak.presentation.my_coupons.ui_state.MyCouponDataUiState
import app.grand.tafwak.presentation.my_coupons.ui_state.MyCouponsEmptyUiState
import app.grand.tafwak.presentation.my_coupons.ui_state.MyCouponsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MyCouponsUseCase @Inject constructor(
  private val myCouponsRepository: MyCouponsRepository
) {

  operator fun invoke() = flow {
    emit(Resource.Loading)
    val result = myCouponsRepository.getMyCoupons()
    val items = mutableListOf<MyCouponsUiState>()
    if (result is Resource.Success) {
      val data = result.value.data
      if (data.isNullOrEmpty()) {
        items.add(MyCouponsEmptyUiState())
        emit(Resource.Success(items))
      } else {
        data.map {
          items.add(MyCouponDataUiState(it))
        }
        emit(Resource.Success(items))
      }

    } else
      emit(result)
  }.flowOn(Dispatchers.IO)


}