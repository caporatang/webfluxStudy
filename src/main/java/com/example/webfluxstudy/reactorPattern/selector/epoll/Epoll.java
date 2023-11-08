package com.example.webfluxstudy.reactorPattern.selector.epoll;

/**
 * packageName : com.example.webfluxstudy.reactorPattern.selector.epoll
 * fileName : Epoll
 * author : taeil
 * date : 11/8/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/8/23        taeil                   최초생성
 */
public class Epoll {
    // 어떻게 Selector는 busyWait 상태를 거치지 않는데 빠르게 동작할수있을까?

    // Selector 종류
    // 플랫폼마다 최적화된 구현 제공
    // PollSelectorImp : Solaris의 poll 시스템 콜을 활용하는 PollSelector
    // EPollSelectorImpl : Linux에서 사용하는 epoll 기반의 EPollSelector
    // KQueueSelectorImpl : macOS / BSD에서 사용하는 kqueue 기반의 KQueueSelector
    // WindowsSelectorImpl : Windows의 IOCP 기능을 활용한 WindowSelector

    // file descriptor ?
    // 유닉스 계열의 os에서는 일반적인 파일, 네트워크 소켓, 파이프, 블록 디바이스, 캐릭터 디바이스 등 모든 객체를 파일로 관리한다.
    // 열려있는 파일에 접근할 때, fd를 이용해서 파일을 지정
    // fd는 음이 아닌 정수를 사용, file descriptor table의 index로 사용되기 때문이다.
    // 파일을 open하면 fd로 사용하지 않은 가장 작은 값을 할당.
    // 표준 입력, 표준 출력, 표준 에러에 각각 0, 1, 2 라는 fd가 부여된다.

    // fd는 process별로 존재하고, File descriptor 테이블이 별도로 존재하고,
    // 테이블 안에 인덱스별로 적재되어있음
    // 0 -> 입력
    // 1 -> 표준 출력
    // 2 -> 표준 에러

    // File table : 모든 process가 접근 가능 -> read mode, write mode, offset에 대한 정보가 적재되어 있음
    // Inode table : File table의 로우 여러개는 Inode table의 하나의 로우를 바라볼 수 있음

    // epoll
    // linux 2.6 이상에서 지원
    // os가 fd 세트를 관찰하고 I/O가 준비된 fd가 있다면 application 에게 전달
    // macOS/BSD 계열이라면 kqueue, Windows라면 IOCP 지원

    // epoll의 시스템 콜 함수
    // epoll_create : epoll인스턴스를 생성한다.
    // epoll_ctl : epoll 인스턴스에 fd와 관심있는 작업을 등록/삭제/수정한다.
    // epoll_wait : fd와 관련된 이벤트를 감시한다.

}