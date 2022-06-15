package app.te.lima_zola.presentation.auth.subscribe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.lima_zola.R
import app.te.lima_zola.databinding.ItemPaymentMethodBinding
import app.te.lima_zola.domain.auth.entity.model.payments.PaymentItem

class PaymentMethodsAdapter :
    RecyclerView.Adapter<PaymentMethodsAdapter.ViewHolder>() {
    var lastPosition: Int = 0
    private val differCallback = object : DiffUtil.ItemCallback<PaymentItem>() {
        override fun areItemsTheSame(
            oldItem: PaymentItem,
            newItem: PaymentItem
        ): Boolean {
            return oldItem.paymentId == newItem.paymentId
        }

        override fun areContentsTheSame(
            oldItem: PaymentItem,
            newItem: PaymentItem
        ): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_payment_method, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.itemLayoutBinding.radioName.setOnClickListener {
            notifyItemChanged(lastPosition)
            lastPosition = position
            notifyItemChanged(lastPosition)
        }
        data.isSelected = position == lastPosition

        holder.setModel(data)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unBind()
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var itemLayoutBinding: ItemPaymentMethodBinding

        init {
            bind()
        }

        fun bind() {
            itemLayoutBinding = DataBindingUtil.bind(itemView)!!
        }

        fun unBind() {
            itemLayoutBinding.unbind()
        }

        fun setModel(item: PaymentItem) {
            itemLayoutBinding.model = item

        }

        fun updateRadioCheck(checked: Boolean) {
            itemLayoutBinding.radioName.isChecked = checked
        }
    }

}