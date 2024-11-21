package com.wavelinemedia.medicationreminderalarmbroadcast.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.wavelinemedia.medicationreminderalarmbroadcast.data.local.ReminderDao
import com.wavelinemedia.medicationreminderalarmbroadcast.data.local.ReminderDatabase
import com.wavelinemedia.medicationreminderalarmbroadcast.data.repository.ReminderRepoImpl
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.repository.ReminderRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ReminderDatabase {
        return ReminderDatabase.getInstance(context)
    }

    @Provides
    fun provideDao(reminderDatabase: ReminderDatabase): ReminderDao {
        return reminderDatabase.getReminderDao()
    }


    @Provides
    fun provideReminderRepo(reminderDao: ReminderDao): ReminderRepository {
        return ReminderRepoImpl(reminderDao)
    }
}