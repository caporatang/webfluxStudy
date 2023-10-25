package com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer;

/**
 * packageName : com.example.webfluxstudy.javaIoNioAio.javaNIO.buffer
 * fileName : ChannelAndBuffer
 * author : taeil
 * date : 2023/10/25
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/25        taeil                   최초생성
 */
public class ChannelAndBuffer {
    // 데이터를 읽을 때 : 적절한 크기의 Buffer를 생성하고 Channel의 read() 메서드를 사용하여 데이터를 Buffer에 저장
    // 데이터를 쓸 때 : 먼저 BUffer에 데이터를 저장하고 Channel의 write() 메서드를 사용하여 목적지로 전달
    // clear() 메서드로 초기화하여 다시 사용 가능


    // Buffer 종류
    // ByteBUffer : byte 단위로, HeapByteBuffer DirectByteBuffer 가 있음
    // CharBuffer
    // short
    // Int
    // Long
    // Float
    // Double

    // Buffer는 값을 읽고 쓸 수 있다. -> 위치 속성을 갖는다
    // capacity : Buffer가 저장할 수 있는 데이터의 최대 크기. Buffer 생성시 결정되며 변경 불가.
    // position : Buffer에서 현재 위치를 가리킨다. 버퍼에서 데이터를 읽거나 쓸 때, 해당 위치부터 시작한다. Buffer에 1Byte가 추가될 때마다 1 증가한다.
    // limit : Buffer에서 데이터를 읽거나 쓸 수 있는 마지막 위치. limit 이후로 데이터를 읽거나 쓰기 불가. 최초 생성시 capacity와 동일하다.
    // mark : 현재 position 위치를 mark() 로 지정할 수 있고 reset() 호출시 position을 mark로 이동..! -> 사용 빈도가 떨어진다.
    // 0 <= mark <= position(위치) <= limit(위치) <= capacity(크기)

    // Buffer 위치 메서드 - flip
    // Buffer의 limit 위치를 현재 position 위치로 이동시키고, position을 0으로 리셋한다.
    // Buffer를 쓰기 모드에서 읽기 모드로 전환하는 경우 사용

    // Buffer 위치 메서드 - rewind : Buffer의 position 위치를 0으로 리셋한다. -> flip과의 차이점은 limit은 변경하지 않는다
    // 데이터를 처음부터 다시 읽는 경우 사용한다.

    // Buffer 위치 메서드 - clear()
    // Buffer의 limit 위치를 capacity 위치로 이동시키고, position을 0으로 리셋 시킨다.
    // Buffer를 초기화할 때 사용한다.
}