package ch.bfh.cas.mad.myapplication.Widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import ch.bfh.cas.mad.myapplication.MainActivity
import ch.bfh.cas.mad.myapplication.R
import ch.bfh.cas.mad.myapplication.helper.CounterPreferences

class MyWidgetProvider : AppWidgetProvider() {

    companion object {
        const val ACTION_INCREMENT_COUNTER1 = "ACTION_INCREMENT_COUNTER1"
        const val ACTION_INCREMENT_COUNTER2 = "ACTION_INCREMENT_COUNTER2"
        const val ACTION_INCREMENT_COUNTER3 = "ACTION_INCREMENT_COUNTER3"
        const val ACTION_INCREMENT_COUNTER4 = "ACTION_INCREMENT_COUNTER4"
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        val counterPreferences = CounterPreferences(context)
        appWidgetIds.forEach { appWidgetId ->
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_layout)

            // Intent für Counter 1
            val intent1 = Intent(context, MainActivity::class.java)
            intent1.action = ACTION_INCREMENT_COUNTER1
            val pendingIntent1 = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
            remoteViews.setOnClickPendingIntent(R.id.button1, pendingIntent1)
            remoteViews.setTextViewText(R.id.button1, "Counter 1: ${counterPreferences.getCounter(1)}")


            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }
}