package ch.bfh.cas.mad.myapplication.helper
import android.content.Context

class CounterPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("CounterPreferences", Context.MODE_PRIVATE)

    fun getCounter(counterId: Int): Int {
        return sharedPreferences.getInt("counter$counterId", 0)
    }

    fun incrementCounter(counterId: Int) {
        val currentCount = getCounter(counterId)
        sharedPreferences.edit().putInt("counter$counterId", currentCount + 1).apply()
    }
}