package app.te.protein_chef.appTutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.te.protein_chef.appTutorial.TutorialAdapter.ImagesSliderViewHolder
import coil.ImageLoader
import coil.request.ImageRequest
import app.te.protein_chef.domain.intro.entity.AppTutorial
import app.te.protein_chef.R
import app.te.protein_chef.databinding.ItemTutorialBinding

internal class TutorialAdapter(
  private var titleColor: Int,
  private var contentColor: Int
) : ListAdapter<AppTutorial, ImagesSliderViewHolder>(DIFF_CALLBACK) {
  private lateinit var context: Context

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AppTutorial>() {
      override
      fun areItemsTheSame(oldItem: AppTutorial, newItem: AppTutorial): Boolean =
        oldItem.hashCode() == newItem.hashCode()

      override
      fun areContentsTheSame(oldItem: AppTutorial, newItem: AppTutorial): Boolean =
        oldItem == newItem
    }
  }

  override
  fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ImagesSliderViewHolder {
    val root = LayoutInflater.from(parent.context).inflate(R.layout.item_tutorial, parent, false)
    context = parent.context
    return ImagesSliderViewHolder(ItemTutorialBinding.bind(root))
  }

  override
  fun onBindViewHolder(holder: ImagesSliderViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class ImagesSliderViewHolder(private val itemBinding: ItemTutorialBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var currentItem: AppTutorial

    init {
      itemBinding.tvTitle.setTextColor(titleColor)
      itemBinding.tvContent.setTextColor(contentColor)
    }

    fun bind(item: AppTutorial) {
      currentItem = item

      itemBinding.tvTitle.text = item.title
      itemBinding.tvContent.text = item.body
      showImage(item.image)
    }

    private fun showImage(imageUrl: String) {
      if (imageUrl.isNotEmpty()) {
        val request = ImageRequest.Builder(context)
          .data(imageUrl)
          .crossfade(true)
          .crossfade(400)
          .placeholder(R.color.colorGray)
          .error(R.drawable.bg_no_image)
          .target(
            onStart = { placeholder ->
              itemBinding.ivImage.setImageDrawable(placeholder)
            },
            onSuccess = { result ->
              itemBinding.ivImage.setImageDrawable(result)
            }
          )
          .listener(onError = { request: ImageRequest, _: Throwable ->
            itemBinding.ivImage.setImageDrawable(request.error)
          })
          .build()

        ImageLoader(context).enqueue(request)
      } else {
        itemBinding.ivImage.setImageResource(R.drawable.bg_no_image)
      }
    }

  }

}