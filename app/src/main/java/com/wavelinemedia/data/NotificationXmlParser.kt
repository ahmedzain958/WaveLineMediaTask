package com.wavelinemedia.data

import android.content.Context
import com.wavelinemedia.task.R
import com.wavelinemedia.domain.Notification
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class NotificationXmlParser(private val context: Context) {
    suspend fun parseNotifications(): List<Notification> {
        val inputStream = context.resources.openRawResource(R.raw.notifications)
        val notifications = mutableListOf<Notification>()

        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        parser.setInput(inputStream, "UTF-8")

        var eventType = parser.eventType
        var notification: Notification? = null

        while (eventType != XmlPullParser.END_DOCUMENT) {
            val tagName = parser.name
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    if (tagName == "notification") {
                        notification = Notification(0, "", 0)
                    } else if (notification != null) {
                        when (tagName) {
                            "id" -> notification.id = parser.nextText().toInt()
                            "title" -> notification.title = parser.nextText()
                            "timeInSeconds" -> notification.timeInSeconds = parser.nextText().toInt()
                        }
                    }
                }
                XmlPullParser.END_TAG -> if (tagName == "notification") {
                    notification?.let { notifications.add(it) }
                }
            }
            eventType = parser.next()
        }
        return notifications
    }
}
