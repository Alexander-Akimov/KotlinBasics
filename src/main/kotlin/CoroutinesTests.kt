import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun testCoroutine() {
    suspend fun workload(n: Int): Int {
        delay(1000)
        return n
    }

    val deferred = (1..1_000_000).map { n ->
        GlobalScope.async {
            //delay(1000) to make sure that our coroutines actually run in parallel.
            workload(n)
        }
    }
    runBlocking {
        val sum = deferred.map { it.await().toLong() }.sum()
        println("Sum: $sum")
    }
}