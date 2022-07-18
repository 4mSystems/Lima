package app.te.lima_zola.presentation.auth.subscribe.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.lima_zola.R
import app.te.lima_zola.databinding.ItemSubscriptionBinding
import app.te.lima_zola.domain.auth.entity.model.subscriptions.SubscriptionsTypes
import app.te.lima_zola.presentation.auth.subscribe.listeners.SubscribeEventListener

class SubscriptionTypesAdapter(val eventListener: SubscribeEventListener) :
    RecyclerView.Adapter<SubscriptionTypesAdapter.ViewHolder>() {
    var lastPosition: Int = 0
    private val differCallback = object : DiffUtil.ItemCallback<SubscriptionsTypes>() {
        override fun areItemsTheSame(
            oldItem: SubscriptionsTypes,
            newItem: SubscriptionsTypes
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SubscriptionsTypes,
            newItem: SubscriptionsTypes
        ): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_subscription, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.itemLayoutBinding.radioAnnual.setOnClickListener {
            notifyItemChanged(lastPosition)
            lastPosition = position
            notifyItemChanged(lastPosition)
            eventListener.onSubscriptionType(data)
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
        lateinit var itemLayoutBinding: ItemSubscriptionBinding

        init {
            bind()
        }

        fun bind() {
            itemLayoutBinding = DataBindingUtil.bind(itemView)!!
        }

        fun unBind() {
            itemLayoutBinding.unbind()
        }

        fun setModel(item: SubscriptionsTypes) {
            itemLayoutBinding.model = item
        }

        fun updateRadioCheck(checked: Boolean) {
            itemLayoutBinding.radioAnnual.isChecked = checked
        }
    }

}