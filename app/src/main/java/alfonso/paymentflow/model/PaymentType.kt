package alfonso.paymentflow.model

import com.google.gson.annotations.SerializedName

enum class PaymentType {
    @SerializedName("credit_card")  CREDIT_CARD,
    @SerializedName("debit_card")   DEBIT_CARD,
    @SerializedName("prepaid_card") PREPAID_CARD,
    @SerializedName("ticket")       TICKET,
    @SerializedName("atm")          ATM
}