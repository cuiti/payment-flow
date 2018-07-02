package alfonso.paymentflow.model

import com.google.gson.annotations.SerializedName

data class PaymentMethod(val id: String,
                         val name: String,
                         val thumbnail: String,
                         val status: PaymentMethodStatus,
                         @SerializedName("payment_type_id") val paymentType: PaymentType)