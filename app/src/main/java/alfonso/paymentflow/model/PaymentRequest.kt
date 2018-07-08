package alfonso.paymentflow.model

class PaymentRequest{
    var amount: Float = 0f
    var issuer: CardIssuer? = null
    lateinit var method: PaymentMethod
    lateinit var payerCost: PayerCost
}