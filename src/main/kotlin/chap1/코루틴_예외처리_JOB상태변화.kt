package com.chap1

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

/**
 * launch : 예외가 발생하면, 예외를 출력하고 코루틴이 종료
 * async  : 예외가 발생해도, 예외를 출력하지않음. 예외를 확인하려면 await()이 필요
 *
 * 발생한 예외가 CancellationException 인 경우 취소로 간주하고 부모 코루틴에게 전파 X
 * 그 외 다른 예외가 발생한 경우 실패로 간주하고 부모 코루틴에게 전파 O
 *
 * */

fun main() {
    testException()
}

fun throwAsyncException(): Unit = runBlocking() {

    val job = CoroutineScope(Dispatchers.Default).async {
        throw IllegalArgumentException("11")
    }

    delay(1_000L)
    job.await()

}


fun exceptionHandler(): Unit = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler{_, _->
        println("CoroutineExceptionHandler")
    }

    val job1 = CoroutineScope(Dispatchers.Default).launch(exceptionHandler) {
        throw IllegalArgumentException("11")
    }

    delay(1_000L)
}

fun testException(): Unit = runBlocking {
    val async1 = CoroutineScope(Dispatchers.Default).async {
        println("async")
    }


    val launch1 = CoroutineScope(Dispatchers.Default).launch {
        println("launch")
    }


}