package exception_handeling

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield


/**
 * If a coroutine encounters an exception other than CancellationException,
 * it cancels its parent with that exception. This behaviour cannot be overridden and is
 * used to provide stable coroutines hierarchies for structured concurrency.
 * CoroutineExceptionHandler implementation is not used for child coroutines.
 * so thatis why that [parent] was never cancel as the thrown exception was [CancelationException]
 * which is caught and handelde by coroutines machinary
 */
fun main() {

    runBlocking {

        val job = launch {
            val childOfJob = launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {

                    println("childOfJob canceled ")
                }
            }
            yield()
            println("Cancelling Child")
            childOfJob.cancel()
            childOfJob.join()
            yield()
            println("Parent is not canceled ")

        }
        job.join()


    }
}