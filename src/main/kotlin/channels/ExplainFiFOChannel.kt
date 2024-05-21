package channels

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Send and receive operations to channels are fair with respect to the order of
// their invocation from multiple coroutines. They are served in first-in first-out order,
// e.g. the first coroutine to invoke receive gets the element. In the following example
// two coroutines "ping" and "pong" are
// receiving the "ball" object from the shared "table" channel.


data class Ball(var hits: Int)

fun main() {
    runBlocking {

        val table = Channel<Ball>()
        launch { player("ping", table) }
        launch { player("pong", table) }
        table.send(Ball(0))
        delay(1000)
        coroutineContext.cancelChildren()
    }
}

suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) {
        ball.hits++
        println("$name $ball")
        delay(300)
        table.send(ball)

    }
}