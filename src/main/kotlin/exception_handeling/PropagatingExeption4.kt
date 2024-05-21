package exception_handeling

import kotlinx.coroutines.*
import java.io.IOException


fun main() {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    runBlocking {


        val job = GlobalScope.launch(handler) {
            val innerJob = launch { // all this stack of coroutines will get cancelled
                launch {
                    launch {
                        throw IOException() // the original exception
                    }
                }
            }
            try {
                innerJob.join()
            } catch (e: CancellationException) {
                println("Rethrowing CancellationException with original cause")
                throw e // cancellation exception is rethrown, yet the original IOException gets to the handler
            }
        }
        job.join()
    }
}