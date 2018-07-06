package alfonso.paymentflow.main

import alfonso.paymentflow.model.PaymentMethod

interface NavigationProvider {
    fun onAmountSelected(amount: Float)
    fun onPaymentMethodSelected(method: PaymentMethod)
}