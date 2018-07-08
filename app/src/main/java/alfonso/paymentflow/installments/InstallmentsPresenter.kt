package alfonso.paymentflow.installments

import alfonso.paymentflow.BuildConfig
import alfonso.paymentflow.OnOptionClickListener
import alfonso.paymentflow.main.NavigationProvider
import alfonso.paymentflow.model.Installment
import alfonso.paymentflow.model.PayerCost
import alfonso.paymentflow.network.ApiManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class InstallmentsPresenter (private val navigationProvider: NavigationProvider): OnOptionClickListener {

    override fun onOptionClick(item: Any) {
        navigationProvider.onInstallmentsSelected(item as PayerCost)
    }

    fun getPayerCosts(methodId: String, issuerId: String?, amount: Float): Single<List<PayerCost>>{
        return ApiManager.getMercadoPagoService()
                .getInstallments(BuildConfig.ApiKey, amount, issuerId, methodId)
                .map { installments: List<Installment> ->  installments[0].payerCosts}
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError{ error -> navigationProvider.onError(error) }
    }
}