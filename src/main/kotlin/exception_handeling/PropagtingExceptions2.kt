package exception_handeling


import kotlinx.coroutines.*


fun main() {
    runBlocking {
        joinAll(job, deferred)

    }
}

private val handler = CoroutineExceptionHandler { _, exception ->
    println("CoroutineExceptionHandler got $exception")
}
private val job = GlobalScope.launch(handler) { // root coroutine, running in GlobalScope
    throw AssertionError()
}

/**
 * will not throw or do anything untill using [await]
 */
private val deferred = GlobalScope.async(handler) { // also root, but async instead of launch
    throw ArithmeticException() // Nothing will be printed, relying on user to call deferred.await()
}
