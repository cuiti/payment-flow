package alfonso.paymentflow.method

import alfonso.paymentflow.OnOptionClickListener
import alfonso.paymentflow.R
import alfonso.paymentflow.model.CardIssuer
import alfonso.paymentflow.model.PayerCost
import alfonso.paymentflow.model.PaymentMethod
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*

class OptionsAdapter<T>(var listener: OnOptionClickListener) : RecyclerView.Adapter<OptionsAdapter<T>.OptionViewHolder>() {

    var list: List<T> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(parent.context)
                                .inflate(R.layout.list_item, parent, false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OptionViewHolder, pos: Int) = holder.bindTo(list[pos])

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindTo(option: T){
            when (option){
                is PaymentMethod -> bindToPaymentMethod(option)
                is CardIssuer -> bindToCardIssuer(option)
                is PayerCost -> bindToPayerCost(option)
            }
            itemView.setOnClickListener{ listener.onOptionClick(option as Any) }
        }

        private fun bindToPaymentMethod(method: PaymentMethod){
            itemView.itemTextView.text = method.name
            loadIcon(method.thumbnail)
        }

        private fun bindToCardIssuer(issuer: CardIssuer){
            itemView.itemTextView.text = issuer.name
            loadIcon(issuer.thumbnail)
        }

        private fun bindToPayerCost(cost: PayerCost){
            itemView.itemTextView.text = cost.message
        }

        private fun loadIcon(url: String){
            Glide.with(itemView.context)
                    .load(url)
                    .into(itemView.itemImageView)
        }
    }
}