package cancelation_timeouts

import kotlinx.coroutines.*


fun main() = runBlocking {

    val job = launch(Dispatchers.Default) {
        repeat(5) { i ->
            try {
                println("job : iam sleeping ${i}")
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    delay(1300L)
    println("main : Iam tired of waiting !")
    job.cancelAndJoin()
    println("main now i can quit ")

}