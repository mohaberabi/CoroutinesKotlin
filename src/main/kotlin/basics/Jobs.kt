package basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * the [join] tells the current scope , hey if there is a child coroutine wait until it is done then
 * continue executing the current code
 */
fun main() = runBlocking {

    val job = launch {
        delay(1000L)
        println("World 2")
    }


    println("Hello 1 ")
    job.join()
    println("World 3")

}


