package app.te.lima_zola.presentation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.lima_zola.R
import app.te.lima_zola.databinding.ItemHomeBinding
import app.te.lima_zola.presentation.home.HomeFragment
import app.te.lima_zola.presentation.home.eventListener.HomeEventListener
import app.te.lima_zola.presentation.home.ui_state.CategoriesUiItemState

class CategoriesAdapter(val homeEventListener: HomeEventListener) :
  RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
  private val differCallback = object : DiffUtil.ItemCallback<CategoriesUiItemState>() {
    override fun areItemsTheSame(
      oldItem: CategoriesUiItemState,
      newItem: CategoriesUiItemState
    ): Boolean {
      return oldItem.id() == newItem.id()
    }

    override fun areContentsTheSame(
      oldItem: CategoriesUiItemState,
      newItem: CategoriesUiItemState
    ): Boolean {
      return oldItem.toString() == newItem.toString()
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = differ.currentList[position]
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
    lateinit var itemLayoutBinding: ItemHomeBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(item: CategoriesUiItemState) {
      itemLayoutBinding.eventListener = homeEventListener
      itemLayoutBinding.uiState = item
    }
  }

}