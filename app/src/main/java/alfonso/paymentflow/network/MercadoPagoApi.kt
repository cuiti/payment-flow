package alfonso.paymentflow.network

import alfonso.paymentflow.model.CardIssuer
import alfonso.paymentflow.model.Installment
import alfonso.paymentflow.model.PaymentMethod
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadoPagoApi {

    @GET("payment_methods")
    fun getPaymentMethods(@Query("public_key") key: String) : Single<List<PaymentMethod>>

    @GET("payment_methods/card_issuers")
    fun getCardIssuers(@Query("public_key") key: String,
                       @Query("payment_method_id") id: String) : Single<List<CardIssuer>>

    @GET("payment_methods/installments")
    fun getInstallments(@Query("public_key") key: String,
                        @Query("amount") amount: Float,
                        @Query("issuer.id") issuerId: String,
                       @Query("payment_method_id") id: String) : Single<List<Installment>>
}