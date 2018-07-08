package alfonso.paymentflow.installments

import alfonso.paymentflow.R
import alfonso.paymentflow.main.NavigationProvider
import alfonso.paymentflow.method.OptionsAdapter
import alfonso.paymentflow.model.PayerCost
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_option_list.*

class InstallmentsFragment : Fragment() {
    private lateinit var presenter: InstallmentsPresenter
    private lateinit var adapter: OptionsAdapter<PayerCost>
    private lateinit var paymentMethodId: String
    private lateinit var issuerId: String
    private var amount: Float = 0f

    companion object {
        const val keyPaymentMethodId = "id_payment_method_id"
        const val keyIssuerId = "key_issuer_id"
        const val keyAmount = "key_amount"

        fun init(paymentMethodId: String, issuerId: String, amount: Float): InstallmentsFragment {
            val args = Bundle()
            args.putString(keyPaymentMethodId, paymentMethodId)
            args.putFloat(keyAmount, amount)
            args.putString(keyIssuerId, issuerId)
            val fragment = InstallmentsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = InstallmentsPresenter(activity as NavigationProvider)
        adapter = OptionsAdapter(presenter)
        paymentMethodId = arguments?.getString(keyPaymentMethodId)?: ""
        issuerId = arguments?.getString(keyIssuerId)?: ""
        amount = arguments?.getFloat(keyAmount)?:0f
        return inflater.inflate(R.layout.fragment_option_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        optionsFragmentTitle.text = getString(R.string.installments)
        optionsList.layoutManager = LinearLayoutManager(context)
        optionsList.adapter = adapter
        presenter.getPayerCosts(paymentMethodId, issuerId, amount)
                .doAfterTerminate{ optionsFragmentProgress.visibility = View.GONE }
                .subscribe(
                        { costs -> adapter.list = costs },
                        { error -> error.printStackTrace() })
    }

}