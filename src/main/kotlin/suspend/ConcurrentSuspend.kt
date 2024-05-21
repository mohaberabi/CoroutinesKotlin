package suspend

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
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


fun main() {

    example1()
}

/**
 * this time the function takes only as long as longest one of them as they run
 * concurrently in parrallel
 */
fun example1() {
    runBlocking {


        val time = measureTimeMillis {

            val one = async { doSomething1() }

            val two = async { doSomething2() }
            println("reulst :${one.await() + two.await()}")
        }

        println(
            "time taken : ${
                time
            }"
        )

    }
}

/**
 * this time the function takes only as long as longest one of them as they run
 * concurrently in parrallel but it will execute only if the [await] was called on it
 * as it was [start]= [CoroutineStart.LAZY]
 */

fun example2() {
    runBlocking {


        val time = measureTimeMillis {

            val one = async(start = CoroutineStart.LAZY) { doSomething1() }

            val two = async(start = CoroutineStart.LAZY) { doSomething2() }
            println("reulst :${one.await() + two.await()}")
        }

        println(
            "time taken : ${
                time
            }"
        )

    }
}