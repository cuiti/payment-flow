package alfonso.paymentflow.amount

import alfonso.paymentflow.R
import alfonso.paymentflow.main.NavigationProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_amount.*

class AmountFragment : Fragment(){

    private lateinit var presenter: AmountPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_amount, container, false)
        presenter = AmountPresenter(activity as NavigationProvider)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        amountContinueButton.setOnClickListener{
            if (!presenter.handleAmountInput(amountInput.text.toString()))
                setError(getString(R.string.invalid_amount))
        }
    }

    private fun setError(error: String){
        amountInput.error = error
        amountInput.requestFocus()
    }
}