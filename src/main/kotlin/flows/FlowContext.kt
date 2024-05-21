package flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

/**
 * Exception in thread "main" java.lang.IllegalStateException: Flow invariant is violated:
 * 		Flow was collected in [BlockingCoroutine{Active}@154d53d1, BlockingEventLoop@2fc6d655],
 * 		but emission happened in [DispatchedCoroutine{Active}@1dff54a8, Dispatchers.Default].
 * 		Please refer to 'flow' documentation or use 'flowOn' instead
 */
private fun simple(): Flow<Int> = flow {
    // The WRONG way to change context for CPU-consuming code in flow builder
    kotlinx.coroutines.withContext(Dispatchers.Default) {
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it in CPU-consuming way
            emit(i) // emit next value
        }
    }
}

fun main() = runBlocking<Unit> {
//    simple().collect { value -> println(value) }
    correctWayToChangeContextOfFlow().collect { value -> println(value) }
}

private fun correctWayToChangeContextOfFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        Thread.sleep(100) // pretend we are computing it in CPU-consuming way
        emit(i) // emit next value
    }
}.flowOn(Dispatchers.Default)

