package cancelation_timeouts

import kotlinx.coroutines.*


fun main() = runBlocking {

    val job = launch {
        try {
            repeat(1000) { i ->
                println("job iam sleeping $i")
                delay(500L)
            }
        } finally {

            withContext(NonCancellable) {
                println("iam running finally")
                delay(1000L)
                println("job: i have just got delayed for 1 second because iam [NonCancellable] ")
            }
        }
    }
    delay(1300L)
    println("iam tired of waiting")
    job.cancelAndJoin()
    println("i can now quit ")
}