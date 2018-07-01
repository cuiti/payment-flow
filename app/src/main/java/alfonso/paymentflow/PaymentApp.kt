package alfonso.paymentflow

import alfonso.paymentflow.network.ApiManager
import android.app.Application

class PaymentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ApiManager.initMpService()
    }
}