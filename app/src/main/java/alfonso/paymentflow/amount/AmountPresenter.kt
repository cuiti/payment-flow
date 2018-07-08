package alfonso.paymentflow.amount

import alfonso.paymentflow.main.NavigationProvider

class AmountPresenter(private val navigationProvider: NavigationProvider){

    fun handleAmountInput(input: String): Boolean{
        return if (isInputValid(input)) {
            navigationProvider.onAmountSelected(input.toFloat())
            true
        }
        else false
    }

    private fun isInputValid(input: String): Boolean{
        return input.isNotBlank() && input.toFloat() > 0f
    }
}