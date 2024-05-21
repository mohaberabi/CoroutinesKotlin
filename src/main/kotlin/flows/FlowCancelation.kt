package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull


/**
 * please note that prints is differes from emitting
 * the eimtted value is the value that collector may use reagrding of any
 * computations or executions made inside of the emitting block of the flow
 *
 * so if the collector collected [simple] it will only  make use of emit(T)
 */
private fun simple(): Flow<Int> = flow {

    for (i in 1..3) {

        delay(100)
        println("Emitting ${i}")
        emit(i)
    }
}

/**
 * this flow is cancelable in time out if it exceeds 250 mls so
 * it's clear that the flow needs 300 mls and the caller allows only 250 mls to execute
 * so it will not emit the last value and will exit the collection
 */
fun main() = runBlocking<Unit> {
    withTimeoutOrNull(250) { // Timeout after 250ms
        simple().collect { value -> println(value) }
    }
    println("Done")
}