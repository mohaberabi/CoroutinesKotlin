package flows

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

private val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
private val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
val startTime = System.currentTimeMillis() // remember the start time

fun main() {
    runBlocking {
        nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string with "zip"
            .collect { value -> // collect and print
                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
            }
        numsToCombine.combine(strsToCombine) { a, b -> "$a -> $b" } // compose a single string with "combine"
            .collect { value -> // collect and print
                println("$value at ${System.currentTimeMillis() - startTime} ms from start when combine")
            }
    }

}

val numsToCombine = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
val strsToCombine = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
