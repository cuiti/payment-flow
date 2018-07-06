package alfonso.paymentflow.model

class PaymentRequest{
    var amount: Float = 0f
    lateinit var method: PaymentMethod
}