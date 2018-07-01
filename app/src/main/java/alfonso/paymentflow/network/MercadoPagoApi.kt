package alfonso.paymentflow.network

import alfonso.paymentflow.model.PaymentMethod
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadoPagoApi {

    @GET("payment_methods")
    fun getPaymentMethods(@Query("public_key") key: String) : Single<List<PaymentMethod>>

}