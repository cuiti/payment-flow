package alfonso.paymentflow.bank

import alfonso.paymentflow.R
import alfonso.paymentflow.main.NavigationProvider
import alfonso.paymentflow.method.OptionsAdapter
import alfonso.paymentflow.model.CardIssuer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_option_list.*

class BankFragment : Fragment() {
    private lateinit var presenter: BankPresenter
    private lateinit var adapter: OptionsAdapter<CardIssuer>
    private lateinit var paymentMethodId: String

    companion object {
        const val keyPaymentMethodId = "id_payment_method_id"
        fun init(id: String): BankFragment{
            val args = Bundle()
            args.putString(keyPaymentMethodId, id)
            val fragment = BankFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = BankPresenter(activity as NavigationProvider)
        adapter = OptionsAdapter(presenter)
        paymentMethodId = arguments?.getString(keyPaymentMethodId)?: ""
        return inflater.inflate(R.layout.fragment_option_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        optionsFragmentTitle.text = getString(R.string.choose_your_bank)
        optionsList.layoutManager = LinearLayoutManager(context)
        optionsList.adapter = adapter
        presenter.getCardIssuers(paymentMethodId)
                .doAfterTerminate{ optionsFragmentProgress.visibility = View.GONE }
                .subscribe(
                        { issuers -> adapter.list = issuers},
                        { error -> error.printStackTrace() }
                )
    }
}