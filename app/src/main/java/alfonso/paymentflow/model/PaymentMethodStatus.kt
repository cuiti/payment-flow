package alfonso.paymentflow.model

import com.google.gson.annotations.SerializedName

enum class PaymentMethodStatus {
    @SerializedName("active")               ACTIVE,
    @SerializedName("deactive")             DEACTIVE,
    @SerializedName("temporally_deactive")  TEMPORALLY_DEACTIVE,
}