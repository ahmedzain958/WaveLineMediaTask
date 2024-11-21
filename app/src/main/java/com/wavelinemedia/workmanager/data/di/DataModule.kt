package com.wavelinemedia.workmanager.data.di
import android.content.Context
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.wavelinemedia.workmanager.data.local.QuoteDao
import com.wavelinemedia.workmanager.data.local.QuoteDatabase
import com.wavelinemedia.workmanager.data.remote.ApiService
import com.wavelinemedia.workmanager.data.repository.QuoteRepoImpl
import com.wavelinemedia.workmanager.domain.repository.QuoteRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Named("wm")
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAPiService(@Named("wm") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context): QuoteDatabase {
        return QuoteDatabase.getInstance(context)
    }

    @Provides
    fun provideQuoteDao(quoteDatabase: QuoteDatabase): QuoteDao {
        return quoteDatabase.getQuoteDao()
    }

    @Provides
    @Singleton
    fun provideWorkmanager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun provideQuoteRepository(workManager: WorkManager, quoteDao: QuoteDao): QuoteRepository {
        return QuoteRepoImpl(workManager, quoteDao)
    }
}












