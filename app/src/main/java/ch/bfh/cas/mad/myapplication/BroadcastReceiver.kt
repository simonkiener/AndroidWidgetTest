package ch.bfh.cas.mad.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ch.bfh.cas.mad.myapplication.Widget.MyWidgetProvider
import ch.bfh.cas.mad.myapplication.Widget.MyWidgetProvider.Companion.ACTION_INCREMENT_COUNTER1
import ch.bfh.cas.mad.myapplication.Widget.MyWidgetProvider.Companion.ACTION_INCREMENT_COUNTER2


/**
 * A simple demo of receiving custom intents.
 * action1 is registered statically in the manifest file and action2 is dynamically registered
 * in the mainActivity code.
 *
 * The variables ACTION1 and ACTION2 are declared in the MainActivity as well.
 */
class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_INCREMENT_COUNTER1) { //is it our action1?
            println("We received an intent for Action1.")
        } else if (intent.action == ACTION_INCREMENT_COUNTER2) { //is it our action2?
            println("We received an intent for Action1.")
        }
    }
}