package cancelation_timeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

//I'm sleeping 0 ...
//I'm sleeping 1 ...
//I'm sleeping 2 ...
//Exception in thread "main" kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 1300 ms
fun main() = runBlocking {

    withTimeout(1300L) {
        repeat(1000) { i ->

            println("iam sleeping ${i}")
            delay(500L)
        }
    }
}