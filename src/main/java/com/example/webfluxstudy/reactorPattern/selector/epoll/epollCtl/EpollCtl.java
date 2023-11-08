package com.example.webfluxstudy.reactorPattern.selector.epoll.epollCtl;

/**
 * packageName : com.example.webfluxstudy.reactorPattern.selector.epoll.epollCtl
 * fileName : EpollCtl
 * author : taeil
 * date : 11/8/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/8/23        taeil                   최초생성
 */
public class EpollCtl {
    // epoll 인스턴스에 fd의 이벤트를 등록/삭제/수정 가능
    // epoll_create로 얻었던 epfd를 인자로 전달
    // 관심있는 fd와 이벤트 종류는 fd와 event를 인자로 전달한다.
    // fd의 이벤트를 등록/삭제/수정 할 것인지를 op인자로 전달한다.

    /*
     *   epfd : 등록할 epoll 인스턴스의 fd
     *   op : fd 에 대해 기대하는 동작
     *   fd : 관찰 대상 fd
     *   반환값 : epoll 인스턴스의 epfd
    */
    //int epoll_ctl(int epfd, int op, int fd, struct epoll_event *event);

    // epoll_ctl의 op 종류
    // EPOLL_CTL ADD : EPOLL_CTL_ADD : event 인자에 지정한 설정으로 epfd의 관심 목록에 fd 인자를 추가 -> 1
    // EPOLL_CTL_DEL : 관심 목록에서 fd를 제거 -> 2
    // EPOLL_CTL_MOD : 등록된 fd의 설정을 event 인자에 지정한 설정으로 변경 -> 3


    // epoll_ctl의 event
    // epoll_ctl에 적용할 설정
    // fd와 어떤 I/O 이벤트를 관찰할지 설정한다.
    // events는 이벤트 상수들을 0개 이상 or 해서 구성한 비트 마스크

    // epoll_ctl의 event 상수
    // EPOLLIN : fd의 read가 준비 완료(0x001)
    // EPOLLOUT : fd의 write가 준비 완료 (0x004)
    // EPOLLRDHUP : socket의 상대방이 연결을 닫은 경우(0x2000)
    // EPOLLPRI : fd 예외가 발생(0x002)
    // EPOLLERR : fd에 오류가 발생(0x008)
    // EPOLLET : 에지 트리거 알림을 요청(1 << 31)

}