import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun fooFlow(): Flow<Int> = flow {
    // flow builder
    println("Flow started")
    for (i in 1..3) {
        delay(100) // pretend we are doing something useful here
        //Thread.sleep(100)
        emit(i) // emit next value
    }
}

fun testFlow() = runBlocking {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)

        }
    }
    // Collect the flow
    fooFlow().collect { value -> println(value) }
}

fun flowsAreColdTest() = runBlocking {
    println("Calling foo...")
    val flow = fooFlow()
    println("Calling collect...")
    flow.collect { value -> println(value) }
    println("Calling collect again...")
    flow.collect { value -> println(value) }
}

fun flowCancellation() = runBlocking {
    fun foo(): Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            println("Emitting $i")
            emit(i)
        }
    }
    withTimeoutOrNull(250) {
        // Timeout after 250ms
        foo().collect { value -> println(value) }
    }
    println("Done")
}