import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

fun foo(): List<Int> = listOf(1, 2, 3)
fun fooSequence(): Sequence<Int> = sequence {
    // sequence builder
    for (i in 1..3) {
        Thread.sleep(500) // pretend we are computing it
        yield(i) // yield next value
    }
}

suspend fun fooSuspend(): List<Int> {
    delay(500)
    return listOf(1, 2, 3)
}

fun main(args: Array<String>): Unit {
    //testCoroutine()

    /* runBlocking {
         fooSuspend()
             .forEach { value -> println(value) }
     }*/
/*      GlobalScope.launch {
          fooSuspend()
              .forEach { value -> println(value) }
      }
*/
    // runBlocking { delay(600) } //Thread.sleep(600)
    fooSequence().forEach { value -> println(value) }

    //testFlow()
    //flowsAreColdTest()
    //flowCancellation()

    println("the end")

}
