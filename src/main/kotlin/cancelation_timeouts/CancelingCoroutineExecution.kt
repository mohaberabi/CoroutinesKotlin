package cancelation_timeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * output :
 *  job: I'm sleeping 0 ...
 * job: I'm sleeping 1 ...
 * job: I'm sleeping 2 ...
 * main: I'm tired of waiting!
 * main: Now I can quit.
 */
fun main() = runBlocking {

    val job = launch {

        repeat(1000) { i ->
            println("Job: I am sleeping $i")
            delay(500L)
        }
    }
    delay(1300L)
    println("main: Iam tired of waiting ")
    job.cancel()
    job.join()
    println("main : Now I Can Quit")
}