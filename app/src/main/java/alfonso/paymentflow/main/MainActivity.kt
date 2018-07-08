package alfonso.paymentflow.main

import alfonso.paymentflow.R
import alfonso.paymentflow.amount.AmountFragment
import alfonso.paymentflow.bank.BankFragment
import alfonso.paymentflow.installments.InstallmentsFragment
import alfonso.paymentflow.method.PaymentMethodFragment
import alfonso.paymentflow.model.CardIssuer
import alfonso.paymentflow.model.PayerCost
import alfonso.paymentflow.model.PaymentMethod
import alfonso.paymentflow.model.PaymentRequest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity(), NavigationProvider {

    private val paymentRequest: PaymentRequest = PaymentRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showAmountSelection()
    }

    private fun showAmountSelection(){
        showFragment(AmountFragment())
    }

    private fun showMethodSelection(){
        showFragment(PaymentMethodFragment())
    }

    private fun showCardIssuerSelection(paymentMethodId: String){
        showFragment(BankFragment.init(paymentMethodId))
    }

    private fun showInstallmentsSelection(paymentMethodId: String, issuerId: String, amount: Float){
        showFragment(InstallmentsFragment.init(paymentMethodId, issuerId, amount))
    }

    override fun onAmountSelected(amount: Float) {
        paymentRequest.amount = amount
        showMethodSelection()
    }

    override fun onPaymentMethodSelected(method: PaymentMethod) {
        paymentRequest.method = method
        showCardIssuerSelection(method.id)
    }

    override fun onCardIssuerSelected(issuer: CardIssuer?) {
        paymentRequest.issuer = issuer
        showInstallmentsSelection(paymentRequest.method.id, issuer?.id?:"0", paymentRequest.amount)
    }

    override fun onInstallmentsSelected(costs: PayerCost) {
        paymentRequest.payerCost = costs
        showValuesDialog()
    }

    private fun showValuesDialog(){
        val values = arrayOf("$"+paymentRequest.amount,
                            paymentRequest.method.name,
                            paymentRequest.issuer?.name,
                            paymentRequest.payerCost.message)

        AlertDialog.Builder(this)
                .setTitle(R.string.thank_you_for_your_purchase)
                .setItems(values) { _, _ -> restart() }
                .setPositiveButton(R.string.done) { _, _ -> restart() }
                .create().show()
    }

    override fun onError(error: Throwable) {
        AlertDialog.Builder(this)
                .setTitle("Ha ocurrido un error")
                .setMessage("Intente nuevamente. \n"+error.localizedMessage)
                .setPositiveButton("Ok"){ _,_ -> restart()}
                .create().show()
    }

    private fun restart() = showAmountSelection()

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .addToBackStack(fragment.tag)
                .replace(R.id.container,fragment)
                .commit()
    }
}