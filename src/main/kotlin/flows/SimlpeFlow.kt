package flows

import kotlinx.coroutines.yield


private fun simple(): List<Int> = listOf(1, 2, 3)


fun main() {
    simple().forEach { i ->
        println(i)
    }
}

/**
 * if we are doing some cpu consuming processes we return a sequence
 */
private fun sequence(): Sequence<Int> = sequence {
    for (i in 1..3) {

        Thread.sleep(100)
        yield(i)
    }
}