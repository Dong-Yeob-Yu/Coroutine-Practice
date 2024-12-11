package com.chap1

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Job 객체 활용 정리
 * start()  : 시작 신호
 * cancel() : 취소 신호
 * join()   : 코루틴이 완료될 떄까지 대기
 * */

fun main() {
    Job1AndJob2()
}

// 코루틴 빌더
fun coroutineBuilder(): Unit = runBlocking {
}


fun runBlocking() {

    // runBlocking -> .Block() 과 같음
    runBlocking {
        printWithThreadName("coroutine")
        // launch -> 결과값이 없는 함수
        launch {
            delay(2_000L)
            printWithThreadName("launch end")
        }
    }

    printWithThreadName("end")
}

fun job(): Unit = runBlocking {
    // start = CoroutineStart.LAZY => 실행이 바로 안되고 대기하게 함. start 함수로 실행해줘야함 안그러면 무한대기
    val job = launch(start = CoroutineStart.LAZY) {
        printWithThreadName("job Launch")
    }
    job.start()
}

// 잡 실패
fun jobCancel() {
    runBlocking {
        val job = launch {
            (1..5).forEach { int ->
                delay(500L)
                printWithThreadName(int.toString())
            }
        }
        delay(1_200L)
        job.cancel()
    }
}

fun Job1AndJob2(): Unit = runBlocking {
    val job1 = launch {
        delay(1_000L)
        printWithThreadName("job 1")
    }
    // join 을 쓰면 코루틴 1이 끝날때까지 대기했다가 잡 2를 실행함. block() 이랑 비슷
    job1.join()

    val job2 = launch {
        delay(1_000L)
        printWithThreadName("job 2")
    }
}