package app.te.lima_zola.presentation.videos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.lima_zola.R
import app.te.lima_zola.databinding.ItemSubCategoryBinding
import app.te.lima_zola.presentation.base.events.BaseContentEventListener
import app.te.lima_zola.presentation.videos.ui_state.SubCategoryItemUiState

class SubCategoriesAdapter(val baseContentEventListener: BaseContentEventListener) :
  RecyclerView.Adapter<SubCategoriesAdapter.ViewHolder>() {
  var lastPosition: Int = 0
  private val differCallback = object : DiffUtil.ItemCallback<SubCategoryItemUiState>() {
    override fun areItemsTheSame(
      oldItem: SubCategoryItemUiState,
      newItem: SubCategoryItemUiState
    ): Boolean {
      return oldItem.id() == newItem.id()
    }

    override fun areContentsTheSame(
      oldItem: SubCategoryItemUiState,
      newItem: SubCategoryItemUiState
    ): Boolean {
      return oldItem.toString() == newItem.toString()
    }
  }
  val differ = AsyncListDiffer(this, differCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.item_sub_category, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = differ.currentList[position]
    data.itemPosition = position
    holder.setModel(data)
  }

  override fun getItemCount(): Int {
    return differ.currentList.size
  }

  fun onItemChange(currentPosition: Int) {
    notifyItemChanged(lastPosition)
    differ.currentList[lastPosition].updateItemStrokeColor(R.color.white)
    differ.currentList[currentPosition].updateItemStrokeColor(R.color.colorPrimary)
    notifyItemChanged(currentPosition)
    //update last position
    lastPosition = currentPosition
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
    lateinit var itemLayoutBinding: ItemSubCategoryBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setModel(item: SubCategoryItemUiState) {
      itemLayoutBinding.eventListener = baseContentEventListener
      itemLayoutBinding.uiState = item
    }
  }

}