package alfonso.paymentflow.method

import alfonso.paymentflow.R
import alfonso.paymentflow.main.NavigationProvider
import alfonso.paymentflow.model.PaymentMethod
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_option_list.*

class PaymentMethodFragment : Fragment(){
    private lateinit var presenter: PaymentMethodPresenter
        private lateinit var adapter: OptionsAdapter<PaymentMethod>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        presenter = PaymentMethodPresenter(activity as NavigationProvider)
        adapter = OptionsAdapter(presenter)
        return inflater.inflate(R.layout.fragment_option_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        optionsFragmentTitle.text = getString(R.string.payment_methods)
        optionsList.layoutManager = LinearLayoutManager(context)
        optionsList.adapter = adapter
        presenter.getCreditCardMethods()
                .doAfterTerminate{ optionsFragmentProgress.visibility = View.GONE }
                .subscribe( { paymentMethods -> adapter.list = paymentMethods },
                            { error -> error.printStackTrace() })
    }
}