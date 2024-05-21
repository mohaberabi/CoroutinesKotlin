package cancelation_timeouts

import kotlinx.coroutines.*

/**
 * even after [job] was cacneled it will continue to execute and print the outputs
 * why? because the cancellation thrown is [CancellationException] which is handeed
 * by the scope, and you did not explicitly checked it so whenever it is thrown you do whatever you want
 */
fun main() = runBlocking {

    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}



















