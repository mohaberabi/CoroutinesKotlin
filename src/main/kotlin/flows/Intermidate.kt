package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


private suspend fun performRequest(request: Int): String {
    delay(1000)
    return "response ${request}"
}

/**
 * any transformer function of the flow differs from that of the collections or sequences ones
 * it acts as same but it can call a suspend function innside of it as we can see below
 */
fun main() = runBlocking<Unit> {
    (1..3).asFlow().map { request ->
        performRequest(request)

    }.collect { response ->
        println(response)
    }
    collectWithLimit()

}

/**
 * takes only the first 2 emitted values
 */
fun collectWithLimit() = runBlocking {
    numbers().take(2).collect { no ->
        println(no)
    }
}

fun numbers(): Flow<Int> = flow {

    try {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    } finally {

        println("Finally in numbers")
    }
}