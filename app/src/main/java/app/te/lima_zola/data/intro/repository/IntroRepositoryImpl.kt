package app.te.lima_zola.data.intro.repository

import app.te.lima_zola.data.intro.data_source.IntroRemoteDataSource
import app.te.lima_zola.domain.intro.entity.AppTutorialModel
import app.te.lima_zola.domain.intro.repository.IntroRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import javax.inject.Inject

class IntroRepositoryImpl @Inject constructor(
  private val remoteDataSource: IntroRemoteDataSource
) : IntroRepository {
  override suspend fun intro(): Resource<BaseResponse<List<AppTutorialModel>>> =
    remoteDataSource.intro()

}