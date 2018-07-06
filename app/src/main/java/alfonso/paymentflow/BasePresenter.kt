package alfonso.paymentflow

open class BasePresenter<T:Any> {
    var presentation: T? = null

    fun onCreateView(presentation: T) {
        this.presentation = presentation
    }

    fun onDestroyView() {
        presentation = null
    }
}