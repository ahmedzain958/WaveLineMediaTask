package com.wavelinemedia.workmanager.data.worker

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import com.wavelinemedia.workmanager.data.local.QuoteDao
import com.wavelinemedia.workmanager.data.mappers.toDomain
import com.wavelinemedia.workmanager.data.remote.ApiService
import com.wavelinemedia.task.R

const val PERIODIC_WORK_REQUEST = "PERIODIC_WORK_REQUEST"

@HiltWorker
class PeriodicWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val apiService: ApiService,
    private val quoteDao: QuoteDao
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        return try {
            val response = apiService.getQuote().toDomain(PERIODIC_WORK_REQUEST)
            quoteDao.insert(response)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val notification = NotificationCompat.Builder(context, "channel")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Quote's")
                        .setContentText(response.quote.plus(" ${response.author}"))
                        .build()
                    NotificationManagerCompat.from(context)
                        .notify(1, notification)
                }
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}