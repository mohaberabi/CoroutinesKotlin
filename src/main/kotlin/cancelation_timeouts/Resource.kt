package cancelation_timeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

var acquired = 0

class Resource {
    init {
        acquired++
    }

    fun close() {
        acquired--
    }
}

/**
 * as you can see we are launching a 10k coroutines and withtimout returns an isnstace of resource
 * then it closes it not always the acquired resource will be [0] it might introduce leaks
 */

fun main() {
    runBlocking {
        repeat(10_000) { // Launch 10K coroutines
            launch {
                val resource = withTimeout(60) {
                    // Timeout of 60 ms
                    delay(50) // Delay for 50 ms
                    Resource() // Acquire a resource and return it from withTimeout block
                }
                resource.close() // Release the resource
            }
        }
    }
    println(acquired) // Print the number of resources still acquired
}