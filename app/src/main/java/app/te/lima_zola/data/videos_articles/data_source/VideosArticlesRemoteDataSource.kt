package app.te.lima_zola.data.videos_articles.data_source

import app.te.lima_zola.data.remote.BaseRemoteDataSource
import app.te.lima_zola.domain.videos_articles.entity.request.AddToWishListRequest
import app.te.lima_zola.domain.videos_articles.entity.request.LikeRequest
import javax.inject.Inject

class VideosArticlesRemoteDataSource @Inject constructor(private val apiService: VideosArticlesServices) :
  BaseRemoteDataSource() {

  suspend fun getSubCategory(cat_id: Int) = safeApiCall {
    apiService.getSubCategory(cat_id)
  }

  suspend fun videosArticles(cat_id: Int, PageIndex: Int) = safeApiCall {
    apiService.videosArticles(cat_id, PageIndex)
  }

  suspend fun favoriteVideos(PageIndex: Int) = safeApiCall {
    apiService.favoriteVideos(PageIndex)
  }

  suspend fun addToWishList(addToWishListRequest: AddToWishListRequest) = safeApiCall {
    apiService.addToWishList(addToWishListRequest)
  }

  suspend fun likeContent(likeRequest: LikeRequest) = safeApiCall {
    apiService.likeContent(likeRequest)
  }

}