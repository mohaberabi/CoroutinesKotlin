package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


private val nums = (1..3).asFlow() // numbers 1..3
private val strs = flowOf("one", "two", "three") // strings

/**
 * Just like the Sequence.zip extension function in the Kotlin standard library,
 * flows have a zip operator that combines the corresponding values of two flows:
 * 1 -> one
 * 2 -> two
 * 3 -> three
 * Note if [nums] updates each 300ms and [strs] uptates each 400ms
 * so the zip still produce same value until the longer executes
 * below  is another example
 */
fun main() {
//    runBlocking {
//        nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string
//            .collect { println(it) } // collect and print
//    }

    runBlocking {
        numsDelayed().zip(strsDelayed()) { a, b -> "$a -> $b" } // compose a single string
            .collect { println(it) } // collect and print
    }
}

fun numsDelayed(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(300)
        emit(i)
    }
}

val nosAsString = mapOf<Int, String>(
    1 to "one",
    2 to "two ",
    3 to "three ",
    4 to "four  ",
    5 to "five   ",
)

fun strsDelayed(): Flow<String> = flow {
    for (i in 1..5) {
        delay(400)
        emit(nosAsString[i]!!)
    }
}