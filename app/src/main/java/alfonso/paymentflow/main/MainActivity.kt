package alfonso.paymentflow.main

import alfonso.paymentflow.BuildConfig
import alfonso.paymentflow.R
import alfonso.paymentflow.amount.AmountFragment
import alfonso.paymentflow.network.ApiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationProvider {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showAmountSelection()
    }

    fun showAmountSelection(){
        showFragment(AmountFragment())
    }

    override fun onAmountSelected(amount: Float) {
        showAmountSelection()
    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.container,fragment)
                .commit()
    }
}