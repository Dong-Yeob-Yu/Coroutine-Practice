package com.chap1

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.measureTime

fun main(): Unit = runBlocking {
    val time = measureTime {
        val job1 = async { apiCall1() }
        val job2 = async { apiCall2() }

        println(job1.await() + job2.await())
    }

    // 2초가 걸림 ( callback depth 낮추기 가능)
    // 주의점 : CoroutineStart.LAZY 옵션을 사용하면 await() 함수를 호출했을 때 계산 결과를 계속 기다린다.
    val time2 = measureTime {
        val job3 = async { apiCall1() }
        val job4 = async { apiCall3(job3.await()) }

        println(job4.await())
    }

    println("Completed in $time ms")
    println("Completed in $time2 ms")

}

fun async(): Unit = runBlocking {
    val job = async {
        3 + 5
    }

    val eight = job.await()     // await -> async 의 결과값을 받아오는 객체
    println(eight)
}


suspend fun apiCall1(): Int {
    delay(1_000L)
    return 1
}

suspend fun apiCall2(): Int {
    delay(1_000L)
    return 2
}
suspend fun apiCall3(num: Int): Int {
    delay(1_000L)
    return num + 2
}