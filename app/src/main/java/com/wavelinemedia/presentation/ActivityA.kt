package com.wavelinemedia.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wavelinemedia.task.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityA : AppCompatActivity() {

    private val viewModel: NotificationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        val adapter = NotificationAdapter { notification ->
            val intent = Intent(this, ActivityB::class.java).apply {
                putExtra("NOTIFICATION", notification)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.notifications.observe(this) { notifications ->
            adapter.submitList(notifications)
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }

        viewModel.loadNotifications()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_a, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cancel_all -> {
                viewModel.cancelAllNotifications()
                Toast.makeText(this, "All notifications canceled", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
