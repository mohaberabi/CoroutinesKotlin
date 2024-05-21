package flows


import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

private fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // pretend we are asynchronously waiting 100 ms
        emit(i) // emit next value
    }
}

/**
 * Since the body of collectLatest takes 300 ms, but new values are emitted every 100 ms, we see
 * that the block is run on every value, but completes only for the last value:
 */
fun main() {
    runBlocking {

        val time = measureTimeMillis {
            simple()
                .collectLatest { value -> // cancel & restart on the latest value
                    println("Collecting $value")
                    delay(300) // pretend we are processing it for 300 ms
                    println("Done $value")
                }
        }
        println("Collected in $time ms")
    }
}