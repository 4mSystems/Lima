package app.te.lima_zola.presentation.base.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.View
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import app.te.lima_zola.R
import com.google.android.material.imageview.ShapeableImageView
import java.io.File
import kotlin.math.abs

fun View.show() {
  if (visibility == View.VISIBLE) return

  visibility = View.VISIBLE
  if (this is Group) {
    this.requestLayout()
  }
}

fun View.hide() {
  if (visibility == View.GONE) return

  visibility = View.GONE
  if (this is Group) {
    this.requestLayout()
  }
}

fun View.invisible() {
  if (visibility == View.INVISIBLE) return

  visibility = View.INVISIBLE
  if (this is Group) {
    this.requestLayout()
  }
}

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("app:fromHtml")
fun TextView.fromHtml(text: String?) {
  if (text != null)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT))
    } else {
      setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY))
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("app:fromResources")
fun TextView.fromResources(resource: Int?) {
  if (resource != null)
    text = resources.getString(resource)
}

@BindingAdapter("app:goneUnless")
fun View.goneUnless(visible: Boolean) {
  visibility = if (visible) View.VISIBLE else View.GONE
  if (this is Group) {
    this.requestLayout()
  }
}

@BindingAdapter("app:rate")
fun RatingBar.rateApp(value: String) {
  if (value.isNotEmpty())
    rating = value.toFloat()
}

fun View.showSnackBar(
  message: String,
  retryActionName: String? = null,
  action: (() -> Unit)? = null
) {
  val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)

  action?.let {
    snackBar.setAction(retryActionName) {
      it()
    }
  }

  snackBar.show()
}

@BindingAdapter(
  value = ["app:loadImage", "app:progressBar", "app:defaultImage"],
  requireAll = false
)
fun ImageView.loadImage(imageUrl: String?, progressBar: ProgressBar?, defaultImage: Any?) {
  if (imageUrl != null && imageUrl.isNotEmpty()) {
    if (URLUtil.isValidUrl(imageUrl)) {
      val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .crossfade(true)
        .crossfade(400)
        .error(R.drawable.bg_no_image)
        .placeholder(R.drawable.logo)
        .target(
          onStart = { placeholder ->
            progressBar?.show()
            setImageDrawable(placeholder)
          },
          onSuccess = { result ->
            progressBar?.hide()
            setImageDrawable(result)
          }
        )
        .listener(onError = { request: ImageRequest, _: Throwable ->
          progressBar?.hide()
          setImageDrawable(request.error)
        })
        .build()

      ImageLoader(context).enqueue(request)
    } else {
      load(File(imageUrl)) {
        crossfade(750) // 75th percentile of a second
        build()
      }
    }

  } else {
    progressBar?.hide()
    load(R.drawable.logo) {
      crossfade(true)
      transformations(
        CircleCropTransformation()
      )
    }
  }
}

@BindingAdapter(value = ["app:loadCircleImage", "app:progressBar"], requireAll = false)
fun ImageView.loadCircleImage(imageUrl: String?, progressBar: ProgressBar?) {
  if (imageUrl != null && imageUrl.isNotEmpty()) {
    val request = ImageRequest.Builder(context)
      .data(imageUrl)
      .crossfade(true)
      .crossfade(400)
      .placeholder(R.color.colorLight)
      .error(R.drawable.bg_no_image)
      .transformations(
        CircleCropTransformation()
      )
      .target(
        onStart = { placeholder ->
          progressBar?.show()
          setImageDrawable(placeholder)
        },
        onSuccess = { result ->
          progressBar?.hide()
          setImageDrawable(result)
        }
      )
      .listener(onError = { request: ImageRequest, _: Throwable ->
        progressBar?.hide()
        setImageDrawable(request.error)
      })
      .build()

    ImageLoader(context).enqueue(request)
  } else {
    progressBar?.hide()

    load(R.drawable.bg_no_image) {
      crossfade(true)
      transformations(
        CircleCropTransformation()
      )
    }
  }
}

@BindingAdapter(value = ["app:loadRoundImage", "app:progressBar"], requireAll = false)
fun ImageView.loadRoundImage(imageUrl: String?, progressBar: ProgressBar?) {
  if (imageUrl != null && imageUrl.isNotEmpty()) {
    val request = ImageRequest.Builder(context)
      .data(imageUrl)
      .crossfade(true)
      .crossfade(400)
      .placeholder(R.color.colorLight)
      .error(R.drawable.bg_no_image)
      .transformations(
        RoundedCornersTransformation(
          resources.getDimension(R.dimen.dimen7)
        )
      )
      .target(
        onStart = { placeholder ->
          progressBar?.show()
          setImageDrawable(placeholder)
        },
        onSuccess = { result ->
          progressBar?.hide()
          setImageDrawable(result)
        }
      )
      .listener(onError = { request: ImageRequest, _: Throwable ->
        progressBar?.hide()
        setImageDrawable(request.error)
      })
      .build()

    ImageLoader(context).enqueue(request)
  } else {
    progressBar?.hide()

    load(R.drawable.bg_no_image) {
      crossfade(true)
      transformations(
        RoundedCornersTransformation(
          resources.getDimension(R.dimen.dimen7)
        )
      )
    }
  }
}

@BindingAdapter("load_drawable")
fun loadDrawable(imageView: ImageView, drawable: Drawable?) {
  imageView.setImageDrawable(drawable)
}

@BindingAdapter("load_drawable")
fun ImageView.loadResourceDrawable(drawable: Int) {
  this.setImageResource(drawable)
}

@BindingAdapter("app:changeBackground")
fun MaterialCardView.changeBackground(background_color: Int) {
  this.setCardBackgroundColor(background_color)
}

@BindingAdapter("app:changeBackground")
fun View.changeBackground(background_color: Int) {
  this.setBackgroundResource(background_color)
}

@BindingAdapter("app:adapter", "app:span", "app:orientation")
fun getItemsV2Binding(
  recyclerView: RecyclerView,
  itemsAdapter: RecyclerView.Adapter<*>?,
  spanCount: String,
  orientation: String
) {
  if (orientation == "1") initVerticalRV(
    recyclerView,
    recyclerView.context,
    spanCount.toInt()
  ) else initHorizontalRV(recyclerView, recyclerView.context, spanCount.toInt())
  recyclerView.adapter = itemsAdapter
}

@SuppressLint("WrongConstant")
fun initVerticalRV(recyclerView: RecyclerView, context: Context?, spanCount: Int) {
  recyclerView.setHasFixedSize(true)
  recyclerView.setItemViewCacheSize(30)
  recyclerView.layoutManager =
    GridLayoutManager(context, spanCount, LinearLayoutManager.VERTICAL, false)
}

@SuppressLint("WrongConstant")
fun initHorizontalRV(recyclerView: RecyclerView, context: Context?, spanCount: Int) {
  recyclerView.setHasFixedSize(true)
  recyclerView.setItemViewCacheSize(30)
  recyclerView.layoutManager =
    GridLayoutManager(context, spanCount, LinearLayoutManager.HORIZONTAL, false)
}

fun RecyclerView.setUpAdapter(
  itemsAdapter: RecyclerView.Adapter<*>?,
  spanCount: String,
  orientation: String
) {
  if (orientation == "1") initVerticalRV(
    this,
    this.context,
    spanCount.toInt()
  ) else initHorizontalRV(this, this.context, spanCount.toInt())
  this.adapter = itemsAdapter

}

fun String.isNumeric(toCheck: String): Boolean {
  val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
  return toCheck.matches(regex)
}

fun Long.toStringMatch(): String {
  return if (abs(this / 1000000) >= 1) {
    (this / 1000000).toString().plus("m")

  } else if (this / 1000 >= 1) {
    (this / 1000).toString().plus("k")
  } else {
    this.toString()
  }
}

@BindingAdapter("app:stroke_color")
fun ShapeableImageView.setupStoke(colorRes: Int) {
  this.setStrokeColorResource(colorRes)
  this.setStrokeWidthResource(R.dimen._1sdp)
}
