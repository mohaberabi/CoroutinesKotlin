package exception_handeling

import kotlinx.coroutines.*

val handeler = CoroutineExceptionHandler { _, ex ->

    println("CoroutineExceptionHandler got ${ex}")
}

fun main() = runBlocking {
    val job = GlobalScope.launch(handeler) {
        launch {
            // launch 1
            try {
                delay(Long.MAX_VALUE)
            } finally {
                // switching the launch 1 to NonCancellable context
                withContext(NonCancellable) {
                    println("Children are cancled but excepion not handeled until all children termiantes")
                    delay(100)
                    println("the first child finishes")
                }
            }

        }
        launch { // the second child
            delay(10)
            println("Second child throws an exception")
            throw ArithmeticException()
        }
    }
    job.join()
}