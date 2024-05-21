package flows

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}


@OptIn(ExperimentalCoroutinesApi::class)
        /**
         * 1: First at 140 ms from start
         * 1: Second at 645 ms from start
         * 2: First at 751 ms from start
         * 2: Second at 1258 ms from start
         * 3: First at 1365 ms from start
         * 3: Second at 1871 ms from start
         */
fun main() = runBlocking {
//    val startTime = System.currentTimeMillis() // remember the start time
//    (1..3).asFlow().onEach { delay(100) } // emit a number every 100 ms
//        .flatMapConcat { requestFlow(it) }
//        .collect { value -> // collect and print
//            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//        }
    flatAndMerge()
}


/**
 * Another flattening operation is to concurrently collect all the incoming
 * flows and merge their values into a single flow so that values are emitted as soon as possible.
 * It is implemented by flatMapMerge and flattenMerge operators. They both accept an optional
 * concurrency parameter that limits the number of concurrent flows that are collected
 * at the same time (it is equal to DEFAULT_CONCURRENCY by default).
 */
//1: First at 179 ms from start
//2: First at 263 ms from start
//3: First at 370 ms from start
//1: Second at 686 ms from start
//2: Second at 768 ms from start
//3: Second at 876 ms from start
fun flatAndMerge() = runBlocking {
    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapMerge { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}