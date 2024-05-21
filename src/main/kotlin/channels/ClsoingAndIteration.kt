package channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


private val channel = Channel<Int>()
fun main() {
    runBlocking {

        launch {
            for (x in 1..5) {
                channel.send(x * x)
            }
            channel.close()
        }

        for (y in channel) {
            println(y)
        }
    }
}