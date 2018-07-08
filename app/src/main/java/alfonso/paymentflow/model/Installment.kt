package alfonso.paymentflow.model

import com.google.gson.annotations.SerializedName

data class Installment(@SerializedName("payer_costs") val payerCosts: List<PayerCost>,
                  @SerializedName("payment_method_id") val paymentMethodId: String,
                  @SerializedName("payment_type_id") val paymentTypeId: String)