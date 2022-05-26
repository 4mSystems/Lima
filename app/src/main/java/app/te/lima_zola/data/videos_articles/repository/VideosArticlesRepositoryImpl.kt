package app.te.lima_zola.data.videos_articles.repository

import app.te.lima_zola.data.videos_articles.data_source.VideosArticlesRemoteDataSource
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.videos_articles.entity.MyVideosPaginate
import app.te.lima_zola.domain.videos_articles.entity.SubCategory
import app.te.lima_zola.domain.videos_articles.entity.request.AddToWishListRequest
import app.te.lima_zola.domain.videos_articles.entity.request.LikeRequest
import app.te.lima_zola.domain.videos_articles.repository.VideosArticlesRepository
import javax.inject.Inject

class VideosArticlesRepositoryImpl @Inject constructor(private val remoteDataSource: VideosArticlesRemoteDataSource) :
  VideosArticlesRepository {


  override suspend fun videosArticles(
    cat_id: Int,
    PageIndex: Int
  ): Resource<BaseResponse<MyVideosPaginate>> =
    remoteDataSource.videosArticles(cat_id, PageIndex)

  override suspend fun favoriteVideos(PageIndex: Int): Resource<BaseResponse<MyVideosPaginate>> =
    remoteDataSource.favoriteVideos( PageIndex)

  override suspend fun getSubCategories(cat_id: Int): Resource<BaseResponse<List<SubCategory>>> =
    remoteDataSource.getSubCategory(cat_id)

  override suspend fun addToWishList(addToWishListRequest: AddToWishListRequest): Resource<BaseResponse<*>> =
    remoteDataSource.addToWishList(addToWishListRequest)

  override suspend fun likeContent(likeRequest: LikeRequest): Resource<BaseResponse<*>> =
    remoteDataSource.likeContent(likeRequest)

}