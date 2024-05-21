package suspend

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


private suspend fun doSomething1(): Int {

    delay(1000L)
    return 13
}

private suspend fun doSomething2(): Int {

    delay(1000L)
    return 20
}


/**
 * code inside a single scope is sequential by default
 * as we saw each emthod takes one second to execute
 * so whenever we print the  time taken from both we find it 2 secnods ???
 * but why ?? its obvious as the code is sequential be defaults so
 * [two] needs to wait for [one] to execute  and [println] will wait for [two] which is
 * already waiting for [one] so [one] takes one second and [two] takes 1 seconds
 * so the main will need 2 seconds to execute and complete
 */
fun main() = runBlocking {
    val time = measureTimeMillis {

        val one = doSomething1()
        val two = doSomething2()
        println("Result : ${one + two}")
    }

    println("Completed in ${time} ms ")
}