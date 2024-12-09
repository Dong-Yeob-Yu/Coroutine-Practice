package com.chap1

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

/**
 * 루틴과 코루틴의 차이
 * 루틴 : 한번 시작되면 멈추지 않는다 -> 루틴이 완료되면 모든 데이터는 메모리에서 삭제 (GC)
 * 코루틴 : 시작되어도 중단됐다가 재개 될수있다. -> 중단됐을땐 데이터 삭제 X
 * */

// runBlocking => 일반 루틴과 코루틴을 연결
fun main(): Unit = runBlocking {
    printWithThreadName("Start")
    // launch -> 반환값이 없는 코루틴을 만든다.
    launch {
        newRoutine()
    }
    yield()
    printWithThreadName("End")
}

// 다른 suspend 함수를 호출 가능
suspend fun newRoutine() {
    val num1 = 1
    val num2 = 2
    // 코루틴을 중단하고 다른 코루틴이 실행되도록함 -> 쓰레드 양보S
    yield()
    printWithThreadName("new Routine")
}

fun printWithThreadName(str: String) {
    println("[${Thread.currentThread().name}] ${str}")
}