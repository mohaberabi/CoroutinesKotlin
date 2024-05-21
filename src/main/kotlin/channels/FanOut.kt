package channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


private fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1 // start from 1
    while (true) {
        send(x++) // produce next
        delay(100) // wait 0.1s
    }
}

private fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}

/**
 * Processor #0 received 1
 * Processor #0 received 2
 * Processor #1 received 3
 * Processor #2 received 4
 * Processor #3 received 5
 * Processor #4 received 6
 * Processor #0 received 7
 * Processor #1 received 8
 * Processor #2 received 9
 * Processor #3 received 10
 */
fun main() {
    runBlocking {
        val producer = produceNumbers()
        repeat(5) { launchProcessor(it, producer) }
        delay(950)
        producer.cancel()
    }
}