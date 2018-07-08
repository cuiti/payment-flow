package alfonso.paymentflow.model

class PaymentRequest{
    var amount: Float = 0f
    lateinit var method: PaymentMethod
    lateinit var issuer: CardIssuer
    lateinit var payerCost: PayerCost
}