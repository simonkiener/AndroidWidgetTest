package ch.bfh.cas.mad.myapplication

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import ch.bfh.cas.mad.myapplication.Widget.MyWidgetProvider
import ch.bfh.cas.mad.myapplication.Widget.MyWidgetProvider.Companion.ACTION_INCREMENT_COUNTER1
import ch.bfh.cas.mad.myapplication.Widget.MyWidgetProvider.Companion.ACTION_INCREMENT_COUNTER2
import ch.bfh.cas.mad.myapplication.Widget.MyWidgetProvider.Companion.ACTION_INCREMENT_COUNTER3
import ch.bfh.cas.mad.myapplication.Widget.MyWidgetProvider.Companion.ACTION_INCREMENT_COUNTER4
import ch.bfh.cas.mad.myapplication.helper.CounterPreferences

class MainActivity : ComponentActivity() {

    private lateinit var counterPreferences: CounterPreferences
    private val receiver = MyReceiver()
    // Initialize the buttons
    private lateinit var buttonCounter1: Button
    private lateinit var buttonCounter2: Button
    private lateinit var buttonCounter3: Button
    private lateinit var buttonCounter4: Button
    // Initialize the textviews
    private lateinit var textCounter1: TextView
    private lateinit var textCounter2: TextView
    private lateinit var textCounter3: TextView
    private lateinit var textCounter4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterPreferences = CounterPreferences(this)

        val filter = IntentFilter().apply {
            addAction(ACTION_INCREMENT_COUNTER1)
            addAction(ACTION_INCREMENT_COUNTER2)
            addAction(ACTION_INCREMENT_COUNTER3)
            addAction(ACTION_INCREMENT_COUNTER4)
        }

        buttonCounter1 = findViewById(R.id.button_counter_1)
        buttonCounter2 = findViewById(R.id.button_counter_2)
        buttonCounter3 = findViewById(R.id.button_counter_3)
        buttonCounter4 = findViewById(R.id.button_counter_4)

        textCounter1 = findViewById(R.id.textview_counter_1)
        textCounter2 = findViewById(R.id.textview_counter_2)
        textCounter3 = findViewById(R.id.textview_counter_3)
        textCounter4 = findViewById(R.id.textview_counter_4)

        buttonCounter1.setOnClickListener {
            @Override
            fun onClick(view: View) {
                val intent = Intent(this@MainActivity, MyWidgetProvider::class.java)
                intent.action = ACTION_INCREMENT_COUNTER1
                sendBroadcast(intent)
            }
        }

        //buttonCounter1.setOnClickListener { incrementCounter(1, textCounter1) }
        buttonCounter2.setOnClickListener { incrementCounter(2, textCounter2) }
        buttonCounter3.setOnClickListener { incrementCounter(3, textCounter3) }
        buttonCounter4.setOnClickListener { incrementCounter(4, textCounter4) }

        registerReceiver(receiver, filter, RECEIVER_NOT_EXPORTED)

    }

    fun incrementCounter(counterId: Int, textView: TextView) {
        counterPreferences.incrementCounter(counterId)
        val currentCount = counterPreferences.getCounter(counterId)
        textView.text = currentCount.toString()
        println("Counter $counterId incremented to $currentCount")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
