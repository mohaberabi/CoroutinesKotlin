package cancelation_timeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout


fun main() {
    runBlocking {

        repeat(10_000) { i ->

            launch {
                var resource: Resource? = null
                try {
                    withTimeout(60) {
                        delay(50)
                        resource = Resource()
                    }
                } finally {

                    resource?.close()
                }
            }

        }


    }
    println(acquired) // Print the number of resources still acquired
}