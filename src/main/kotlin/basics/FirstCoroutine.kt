package basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    exampleOne()
}

/**
 * output : Hello
 *          World
 *          you started a new corutoine and then
 *          inside it you started another one inside  the [launch] block
 *          so they both worked and executed in a parrallel or concurrently
 *          as each fo them now runs on differenct scopes
 */

suspend fun exampleOne() = runBlocking {

    launch {
        delay(1000L)
        println("World")
    }

    println("Hello")

}

/**
 * output : World
 *          Hello
 * now this differs from the previous method as sthey are now both run on same scope
 * so the coroutine is suspended by the [delay] to finish then it will execute sequentily
 */

suspend fun exampleTwo() = runBlocking {
    delay(1000L)
    println("World")
    println("Hello")


}


