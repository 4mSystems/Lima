package app.te.lima_zola.domain.videos_articles.repository

import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.videos_articles.entity.MyVideosPaginate
import app.te.lima_zola.domain.videos_articles.entity.SubCategory
import app.te.lima_zola.domain.videos_articles.entity.VideoData
import app.te.lima_zola.domain.videos_articles.entity.request.AddToWishListRequest
import app.te.lima_zola.domain.videos_articles.entity.request.LikeRequest

interface VideosArticlesRepository {
    suspend fun videosArticles(
        cat_id: Int,
        PageIndex: Int
    ): Resource<BaseResponse<MyVideosPaginate>>

    suspend fun favoriteVideos(PageIndex: Int): Resource<BaseResponse<MyVideosPaginate>>
    suspend fun getSubCategories(cat_id: Int): Resource<BaseResponse<List<SubCategory>>>
    suspend fun addToWishList(addToWishListRequest: AddToWishListRequest): Resource<BaseResponse<*>>
    suspend fun likeContent(likeRequest: LikeRequest): Resource<BaseResponse<*>>
    suspend fun docDetails(doc_id: Int): Resource<BaseResponse<VideoData>>

}