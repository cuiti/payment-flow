package alfonso.paymentflow.main

import alfonso.paymentflow.model.CardIssuer
import alfonso.paymentflow.model.PayerCost
import alfonso.paymentflow.model.PaymentMethod

interface NavigationProvider {
    fun onAmountSelected(amount: Float)
    fun onPaymentMethodSelected(method: PaymentMethod)
    fun onCardIssuerSelected(issuer: CardIssuer)
    fun onInstallmentsSelected(costs: PayerCost)
}