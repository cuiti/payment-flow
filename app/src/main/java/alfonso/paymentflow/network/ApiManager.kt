package alfonso.paymentflow.network

import alfonso.paymentflow.BuildConfig
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    private lateinit var service: MercadoPagoApi

    fun initMpService() {
        service = initRetroFit().create(MercadoPagoApi::class.java)
    }

    fun getMercadoPagoService() = service

    private fun initRetroFit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build()
    }
}