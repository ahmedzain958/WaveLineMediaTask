package com.wavelinemedia.cleanarchihilt.data.di

import android.content.Context
import androidx.room.Room
import com.wavelinemedia.cleanarchihilt.data.WaveLineMediaRepositoryImpl
import com.wavelinemedia.cleanarchihilt.data.di.NetworkConfig.BASE_URL
import com.wavelinemedia.cleanarchihilt.data.di.NetworkConfig.DATABASE_NAME
import com.wavelinemedia.cleanarchihilt.data.local.LocalDataSource
import com.wavelinemedia.cleanarchihilt.data.local.LocalDataSourceImpl
import com.wavelinemedia.cleanarchihilt.data.local.WaveLineMediaDao
import com.wavelinemedia.cleanarchihilt.data.local.WaveLineMediaDatabase
import com.wavelinemedia.cleanarchihilt.data.remote.RemoteDataSource
import com.wavelinemedia.cleanarchihilt.data.remote.RemoteDataSourceImpl
import com.wavelinemedia.cleanarchihilt.data.remote.WaveLineMediaApiService
import com.wavelinemedia.cleanarchihilt.domain.WaveLineMediaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build()
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): WaveLineMediaDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WaveLineMediaDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDao(waveLineMediaDatabase: WaveLineMediaDatabase) = waveLineMediaDatabase.dao

    @Provides
    fun provideApiService(retrofit: Retrofit): WaveLineMediaApiService =
        retrofit.create(WaveLineMediaApiService::class.java)

    @Provides
    fun provideLocalDataSource(waveLineMediaDao: WaveLineMediaDao): LocalDataSource =
        LocalDataSourceImpl(waveLineMediaDao)

    @Provides
    fun provideRemoteDataSource(waveLineMediaApiService: WaveLineMediaApiService): RemoteDataSource =
        RemoteDataSourceImpl(waveLineMediaApiService)


    @Provides
    @Singleton
    fun provideWaveLineMediaRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        dispatcher: CoroutineDispatcher,
    ): WaveLineMediaRepository {
        return WaveLineMediaRepositoryImpl(remoteDataSource, localDataSource, dispatcher)
    }


}

object NetworkConfig {
    const val BASE_URL = "https://projectname-5ee14.firebaseio.com/"
    const val DATABASE_NAME = "wavelinemedia_database"
}
