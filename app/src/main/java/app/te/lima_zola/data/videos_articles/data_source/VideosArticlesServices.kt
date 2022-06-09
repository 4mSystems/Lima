package app.te.lima_zola.data.videos_articles.data_source

import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.videos_articles.entity.MyVideosPaginate
import app.te.lima_zola.domain.videos_articles.entity.SubCategory
import app.te.lima_zola.domain.videos_articles.entity.VideoData
import app.te.lima_zola.domain.videos_articles.entity.request.AddToWishListRequest
import app.te.lima_zola.domain.videos_articles.entity.request.LikeRequest
import retrofit2.http.*

interface VideosArticlesServices {
    @GET("v1/app/category/sub_categories/{cat_id}")
    suspend fun getSubCategory(
        @Path("cat_id") cat_id: Int
    ): BaseResponse<List<SubCategory>>

    @GET("v1/app/category/posts/{cat_id}")
    suspend fun videosArticles(
        @Path("cat_id") cat_id: Int,
        @Query("page") PageIndex: Int
    ): BaseResponse<MyVideosPaginate>

    @GET("v1/user/favorites")
    suspend fun favoriteVideos(
        @Query("page") PageIndex: Int
    ): BaseResponse<MyVideosPaginate>

    @GET("v1/app/post/details/{doc_id}")
    suspend fun docDetails(
        @Path("doc_id") doc_id: Int
    ): BaseResponse<VideoData>

    @POST("v1/user/favorite/store")
    suspend fun addToWishList(
        @Body addToWishListRequest: AddToWishListRequest
    ): BaseResponse<*>

    @POST("v1/user/post/like/store")
    suspend fun likeContent(
        @Body likeRequest: LikeRequest
    ): BaseResponse<*>

}