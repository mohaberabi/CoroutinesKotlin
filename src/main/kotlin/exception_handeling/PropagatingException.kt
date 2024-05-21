package exception_handeling

import kotlinx.coroutines.*


fun main() {
    runBlocking {
        val job = GlobalScope.launch {
            println("Throwing Exception from launch")
            throw IndexOutOfBoundsException()
        }
        job.join() // awaits for the [runblocking] job
        println("joined failed job")
        val deffered = GlobalScope.async {
            println("throwing exception from async ")
            throw ArithmeticException()
        }

        try {
            deffered.await()
            println("Unreached")
        } catch (e: ArithmeticException) {
            println("Caught ArithmeticException")

        }
    }
}

