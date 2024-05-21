package exception_handeling

import kotlinx.coroutines.*
import java.io.IOException

/**
 * When multiple children of a coroutine fail with an exception,
 * the general rule is "the first exception wins", so the first exception gets handled.
 * All additional exceptions that happen after the first one are attached
 * to the first exception as suppressed ones.
 */

@OptIn(DelicateCoroutinesApi::class)

fun main() = runBlocking {

    val handeler = CoroutineExceptionHandler { _, ex ->
        println("CoroutineExceptionHandler got $ex with suppressed ${ex.suppressed.contentToString()}")

    }
    val job = GlobalScope.launch(handeler) {
        launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {

                // this is handeled as it was the first one to be thrown
                throw ArithmeticException()
            }
        }

        launch {
            delay(100)

            // this will be suppressed to the first thrown exception
            throw IOException()
        }
        delay(Long.MAX_VALUE)

    }
    job.join()
}