package alfonso.paymentflow.method

import alfonso.paymentflow.BuildConfig
import alfonso.paymentflow.OnOptionClickListener
import alfonso.paymentflow.main.NavigationProvider
import alfonso.paymentflow.model.PaymentMethod
import alfonso.paymentflow.model.PaymentMethodStatus
import alfonso.paymentflow.model.PaymentType
import alfonso.paymentflow.network.ApiManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class PaymentMethodPresenter(private val navigationProvider: NavigationProvider): OnOptionClickListener {

    /* Returns only active credit card payment methods*/
    fun getCreditCardMethods() : Single<List<PaymentMethod>> {
        return ApiManager.getMercadoPagoService()
                .getPaymentMethods(BuildConfig.ApiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .flatMapIterable { mm -> mm }
                .filter{ method -> method.status == PaymentMethodStatus.ACTIVE}
                .filter{ method -> method.paymentType == PaymentType.CREDIT_CARD}
                .toList()
    }

    override fun onOptionClick(item: Any) {
        navigationProvider.onPaymentMethodSelected(item as PaymentMethod)
    }
}