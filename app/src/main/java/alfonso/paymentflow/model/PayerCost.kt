package alfonso.paymentflow.model

import com.google.gson.annotations.SerializedName

data class PayerCost(val installments: Int,
                     val labels: List<String>,
                     @SerializedName("recommended_message") val message: String)