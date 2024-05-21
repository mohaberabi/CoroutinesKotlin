package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


private fun simple(): Flow<Int> = flow {

    for (i in 1..3) {
        delay(100)
        emit(i)
    }

}


/**
 * I'm not blocked 1
 * 1
 * I'm not blocked 2
 * 2
 * I'm not blocked 3
 * 3
 *
 */
fun main() = runBlocking<Unit> {

    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }
    simple().collect { value ->
        println(value)
    }


}