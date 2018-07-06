package alfonso.paymentflow.method

import alfonso.paymentflow.OnOptionClickListener
import alfonso.paymentflow.R
import alfonso.paymentflow.model.PaymentMethod
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*

class OptionsAdapter(var listener: OnOptionClickListener) : RecyclerView.Adapter<OptionsAdapter.OptionViewHolder>() {

    var paymentMethods: List<PaymentMethod> = ArrayList()
        set(value) { field = value; notifyDataSetChanged()}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int = paymentMethods.size

    override fun onBindViewHolder(holder: OptionViewHolder, pos: Int) = holder.bindTo(paymentMethods[pos])

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindTo(method: PaymentMethod){
            itemView.itemTextView.text = method.name
            itemView.setOnClickListener{ listener.onOptionClick(method) }
            Glide.with(itemView.context)
                    .load(method.thumbnail)
                    .into(itemView.itemImageView)
        }
    }
}