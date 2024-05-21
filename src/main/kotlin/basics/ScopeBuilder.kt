package basics

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {

    doWorld()
}

// output Hello World

private suspend fun doWorld() = coroutineScope {

    launch {
        delay(1000L)
        println("World")
    }
    println("Hello")
}
