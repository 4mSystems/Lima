package app.grand.tafwak.data.home.data_source.local

import app.grand.tafwak.domain.home.models.HomeMainData
import javax.inject.Inject

class HomeLocalRemoteDataSource @Inject constructor(private val homeDao: HomeDao) {

  fun homeStudentLocal() = homeDao.getNews()
  suspend fun insertHomeStudent(homeMainData: HomeMainData)=homeDao.insertHomeData(homeMainData)
  fun homeStudentDelete() = homeDao.deleteHomeData()

}