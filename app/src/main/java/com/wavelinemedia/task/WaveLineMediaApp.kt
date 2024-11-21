package com.wavelinemedia.task

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.wavelinemedia.shopper_fk.data.di.dataModule
import com.wavelinemedia.shopper_fk.domain.di.domainModule
import com.wavelinemedia.shopper_fk.presentation.di.presentationModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.core.context.startKoin
import javax.inject.Inject

const val CHANNEL = "channel"
const val NAME = "name"

@HiltAndroidApp
class WaveLineMediaApp: Application() {


    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory


    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(domainModule, dataModule, presentationModule))
        }
       /* WorkManager.initialize(
            this, Configuration.Builder()
                .setWorkerFactory(hiltWorkerFactory).build()
        )
*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(CHANNEL, NAME, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager =
                this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}