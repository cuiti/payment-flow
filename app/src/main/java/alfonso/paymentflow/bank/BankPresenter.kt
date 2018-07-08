package alfonso.paymentflow.bank

import alfonso.paymentflow.BuildConfig
import alfonso.paymentflow.OnOptionClickListener
import alfonso.paymentflow.main.NavigationProvider
import alfonso.paymentflow.model.CardIssuer
import alfonso.paymentflow.network.ApiManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class BankPresenter(private val navigationProvider: NavigationProvider): OnOptionClickListener {

    override fun onOptionClick(item: Any) {
        navigationProvider.onCardIssuerSelected(item as CardIssuer)
    }

    fun getCardIssuers(id: String): Single<List<CardIssuer>>{
        return ApiManager.getMercadoPagoService()
                .getCardIssuers(BuildConfig.ApiKey, id)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError{ error -> navigationProvider.onError(error) }
                .doOnSuccess { issuers ->
                    if (issuers.isEmpty()) navigationProvider.onCardIssuerSelected(null) }
    }
}