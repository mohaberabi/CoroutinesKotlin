package basics

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * output
 * Hello -> World 1 , World 2 , Done
 */


fun main() = runBlocking {

    doWorld()
    println("Done")
}


private suspend fun doWorld() = coroutineScope {

    launch {

        delay(2000L)
        println("World 2 ")

    }

    launch {
        delay(1000L)
        println("World 1 ")
    }

    println("Hello")
}