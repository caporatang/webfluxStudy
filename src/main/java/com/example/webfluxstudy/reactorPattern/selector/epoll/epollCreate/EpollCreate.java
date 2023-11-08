package com.example.webfluxstudy.reactorPattern.selector.epoll.epollCreate;

/**
 * packageName : com.example.webfluxstudy.reactorPattern.selector.epoll.epollCreate
 * fileName : EpollCreate
 * author : taeil
 * date : 11/8/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/8/23        taeil                   최초생성
 */
public class EpollCreate {
    // epoll_create
    // epoll 인스턴스를 생성하고 epoll 인스턴스의 fd 를 반환한다.
    // size는 처음 자료 구조 크기를 할당할 때 힌트로 사용한다. -> linux 2.6.8 부터는 무시 : 하위호환성을 위해 0보다 커야한다.

    // epoll 인스턴스는 관심 목록과 준비 목록 포함
    // 관심 목록 : 감시하기 위해서 등록된 fd세트
    // 준비 목록 : I/O 준비 상태인 fd 세트, 준비 목록은 관심 목록의 부분 집합


    // size : 이벤트 집합 자료 구조를 처음 할당할 때 크기에 대한 힌트 제공.
    // 반환값 : epoll 인스턴스의 epfd
    //int epoll_create(int size);

}