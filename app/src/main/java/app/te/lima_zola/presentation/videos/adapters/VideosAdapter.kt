package app.te.lima_zola.presentation.videos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.lima_zola.R
import app.te.lima_zola.presentation.base.events.BaseContentEventListener
import app.te.lima_zola.presentation.videos.ui_state.MainContentUiState

class VideosAdapter(val baseContentEventListener: BaseContentEventListener) :
    PagingDataAdapter<MainContentUiState, VideosAdapter.ViewHolder>(differCallback) {
    lateinit var context: Context

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<MainContentUiState>() {
            override fun areItemsTheSame(
                oldItem: MainContentUiState,
                newItem: MainContentUiState
            ): Boolean {
                return oldItem.getId() == newItem.getId()
            }

            override fun areContentsTheSame(
                oldItem: MainContentUiState,
                newItem: MainContentUiState
            ): Boolean {
                return oldItem.toString() == newItem.toString()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.getLayoutRes() ?: R.layout.item_video
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position), position)
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: MainContentUiState?, position: Int) {
            item?.bind(itemView, position, context, baseContentEventListener)
        }
    }

}