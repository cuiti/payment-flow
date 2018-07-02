package alfonso.paymentflow.amount

import alfonso.paymentflow.main.NavigationProvider

class AmountPresenter(private val navigationProvider: NavigationProvider,
                      var presentation: AmountPresentation){

    fun onContinueClicked(input: String){
        if (input.isEmpty()) presentation.setError("Ingrese un monto")
        else checkValidInput(input)
    }

    private fun checkValidInput(input: String){
        navigationProvider.onAmountSelected(input.toFloat())
    }
}