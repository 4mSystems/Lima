package app.te.lima_zola.data.home.repository

import app.te.lima_zola.data.home.data_source.remote.HomeRemoteDataSource
import app.te.lima_zola.domain.home.models.HomeData
import app.te.lima_zola.domain.home.repository.HomeRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: HomeRemoteDataSource) :
  HomeRepository {
  override suspend fun getHome(cat_id: Int): Resource<BaseResponse<HomeData>> =
    remoteDataSource.homeStudent(cat_id)
}