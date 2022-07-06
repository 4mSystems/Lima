package app.te.lima_zola.presentation.auth.subscribe

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentSubscribeBinding
import app.te.lima_zola.domain.auth.entity.model.payments.PaymentItem
import app.te.lima_zola.domain.auth.entity.model.payments.payment_result.PaymentData
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.auth.subscribe.adapters.PaymentMethodsAdapter
import app.te.lima_zola.presentation.auth.subscribe.adapters.SubscriptionTypesAdapter
import app.te.lima_zola.presentation.auth.subscribe.listeners.SubscribeEventListener
import app.te.lima_zola.presentation.auth.subscribe.viewModel.SubscribeViewModel
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SubscribeFragment : BaseFragment<FragmentSubscribeBinding>(), SubscribeEventListener {

    private val viewModel: SubscribeViewModel by viewModels()
    private val subscriptionTypesAdapter = SubscriptionTypesAdapter()
    private val paymentAdapter = PaymentMethodsAdapter()

    override
    fun getLayoutId() = R.layout.fragment_subscribe

    override
    fun setBindingVariables() {
        binding.eventListener = this
    }

    override
    fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.subscriptionResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        showShimmer()
                    }
                    is Resource.Success -> {
                        hideShimmer()
                        subscriptionTypesAdapter.differ.submitList(it.value.data.subscriptionTypes)
                        binding.subscribePeriod.adapter = subscriptionTypesAdapter
                        updatePaymentMethods(it.value.data.paymentMethods.paymentItem)

                    }
                    is Resource.Failure -> {
                        hideShimmer()
                        handleApiError(it, retryAction = { viewModel.getSubscriptionTypes() })
                    }

                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.paymentResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        val data = it.value.data.paymentResultData.paymentData
                        if (data.redirectTo.isNotEmpty())
                            openVisaFragment(data.redirectTo)
                        else
                            openPaymentSuccess(data)
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(it, retryAction = { viewModel.getSubscriptionTypes() })
                    }

                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.payDelegateResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        openPayDelegateSuccess()
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(it, retryAction = { viewModel.getSubscriptionTypes() })
                    }

                }
            }
        }
    }

    private fun openPayDelegateSuccess() {
        navigateSafe(SubscribeFragmentDirections.actionSubscribeFragmentToDelegateSuccessDialogFragment())
    }

    private fun openPaymentSuccess(data: PaymentData) {
        navigateSafe(
            SubscribeFragmentDirections.actionSubscribeFragmentToPaymentSuccessFragment(
                data
            )
        )
    }

    private fun openVisaFragment(redirectTo: String) {
        navigateSafe(
            SubscribeFragmentDirections.actionSubscribeFragmentToPayOnlineFragment(redirectTo)
        )
    }

    private fun updatePaymentMethods(paymentMethods: List<PaymentItem>) {
        // add mandoup item to payment list

//        val paymentList: MutableList<PaymentItem> = mutableListOf()
//        paymentList.add(
//            PaymentItem(
//                "",
//                getString(R.string.pay_by_delegate),
//                getString(R.string.pay_by_delegate)
//            )
//        )
//        paymentList.addAll(paymentMethods)
        paymentAdapter.differ.submitList(paymentMethods)
        binding.subscribePayment.adapter = paymentAdapter
    }

    private fun showShimmer() {
        binding.subscriptionShimmer.shimmerFrameLayout.show()
        binding.paymentShimmer.shimmerFrameLayout.show()
    }

    private fun hideShimmer() {
        binding.subscriptionShimmer.shimmerFrameLayout.hide()
        binding.paymentShimmer.shimmerFrameLayout.hide()
    }

    override fun subscribe() {
        if (paymentAdapter.differ.currentList[paymentAdapter.lastPosition].paymentId != 0)
            viewModel.getPaymentResult(
                subscriptionTypesAdapter.differ.currentList[subscriptionTypesAdapter.lastPosition].id,
                paymentAdapter.differ.currentList[paymentAdapter.lastPosition].paymentId
            )
        else
            viewModel.callDelegatePayment(subscriptionTypesAdapter.differ.currentList[subscriptionTypesAdapter.lastPosition].id)
    }

    override fun back() {
        backToPreviousScreen()
    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.colorPrimary)
    }
}