package com.wavelinemedia.data

import android.content.Context
import androidx.work.WorkManager
import com.wavelinemedia.task.domain.NotificationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotificationXmlParser(@ApplicationContext context: Context): NotificationXmlParser {
        return NotificationXmlParser(context)
    }

    @Provides
    @Singleton
    fun provideWorkmanager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideNotificationScheduler(workManager: WorkManager): NotificationScheduler {
        return NotificationScheduler(workManager)
    }

    @Provides
    @Singleton
    fun provideNotificationRepositoryy(
        notificationXmlParser: NotificationXmlParser,
        notificationScheduler: NotificationScheduler,
    ): NotificationRepository {
        return NotificationRepositoryImpl(notificationXmlParser, notificationScheduler)
    }


}
