package com.wavelinemedia.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.wavelinemedia.domain.Notification
import com.wavelinemedia.task.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityB : AppCompatActivity() {
    private val viewModel: NotificationViewModel by viewModels()

    private lateinit var notification: Notification
    private lateinit var titleView: TextView
    private lateinit var scheduleButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        titleView = findViewById(R.id.notificationTitle)
        scheduleButton = findViewById(R.id.scheduleButton)

        notification = intent.getParcelableExtra("NOTIFICATION")
            ?: throw IllegalStateException("Notification is missing")


        titleView.text = notification.title

        scheduleButton.setOnClickListener {
            viewModel.scheduleNotification(notification)
            showConfirmationDialog()
        }

    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Scheduled")
            .setMessage(
                "Notification ${notification.timeInSeconds}" +
                        " seconds has been scheduled successfully."
            )
            .setPositiveButton("OK", null)
            .show()
    }


    private fun cancelNotification(notificationId: Int) {
        viewModel.cancelNotification(notificationId)
        Toast.makeText(
            this,
            "Notification $notificationId with ${notification.timeInSeconds} seconds cancelled",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_b, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cancel -> {
                cancelNotification(notification.id)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}