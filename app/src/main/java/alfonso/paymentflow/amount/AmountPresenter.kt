package alfonso.paymentflow.amount

import alfonso.paymentflow.main.NavigationProvider

class AmountPresenter(private val navigationProvider: NavigationProvider,
                      var presentation: AmountPresentation){

    fun onContinueClicked(input: String){
        if (isInputValid(input))
            navigationProvider.onAmountSelected(input.toFloat())
        else
            presentation.setError("Ingrese un monto válido")
    }

    private fun isInputValid(input: String): Boolean{
        return input.isNotBlank() &&
                input.toFloat() > 0
    }
}