package alfonso.paymentflow.main

import alfonso.paymentflow.BuildConfig
import alfonso.paymentflow.R
import alfonso.paymentflow.amount.AmountFragment
import alfonso.paymentflow.method.PaymentMethodFragment
import alfonso.paymentflow.model.PaymentMethod
import alfonso.paymentflow.model.PaymentRequest
import alfonso.paymentflow.network.ApiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

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

    override fun onAmountSelected(amount: Float) {
        paymentRequest.amount = amount
        showMethodSelection()
    }

    override fun onPaymentMethodSelected(method: PaymentMethod) {
        showAmountSelection()
    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                .replace(R.id.container,fragment)
                .commit()
    }
}