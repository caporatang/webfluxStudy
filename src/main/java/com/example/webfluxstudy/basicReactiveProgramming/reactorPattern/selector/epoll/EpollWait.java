package com.example.webfluxstudy.basicReactiveProgramming.reactorPattern.selector.epoll;

/**
 * packageName : com.example.webfluxstudy.reactorPattern.selector.epoll
 * fileName : EpollWait
 * author : taeil
 * date : 11/8/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/8/23        taeil                   최초생성
 */
public class EpollWait {
    // epoll 인스턴스에서 이벤트를 대기
    // events에 준비가 완료된 fd 목록을 최대 maxevents개 만큼 반환
    // timeout 시간동안 block 되며, -1로 지정하면 하나의 fd라도 준비될 때까지 무한정 대기한다.

    // epfd : 등록할 epoll 인스턴스의 fd
    // events : 준비가 완료된 관심 이벤트 목록
    // maxevents : 관심 이벤트의 최대 수
    // timeout : block할 밀리초
    //int epoll_wait(int epfd, struct epoll_event *events, int maxevents, int timeout);
}