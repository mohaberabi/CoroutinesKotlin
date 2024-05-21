package jobs

import kotlinx.coroutines.*


/**
 * propagates the cancellation to one direction
 * and only cancles all of its children if it is only was canceled
 * it alsoe waits for all children before completetion
 */


fun main() = runBlocking {
    try {
        supervisorScope {
            val child = launch {
                try {
                    println("The child is sleeping")
                    delay(Long.MAX_VALUE)
                } finally {
                    println("The child is cancelled")
                }
            }
            // Give our child a chance to execute and print using yield
            yield()
            println("Throwing an exception from the scope")
            throw AssertionError()
        }
    } catch (e: AssertionError) {
        println("Caught an assertion error")
    }
}