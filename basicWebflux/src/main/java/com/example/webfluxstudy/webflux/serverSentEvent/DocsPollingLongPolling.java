package com.example.webfluxstudy.webflux.serverSentEvent;

/**
 * packageName : com.example.webfluxstudy.serverSentEvent
 * fileName : DocsPollingLongPolling
 * author : taeil
 * date : 2/9/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2/9/24        taeil                   최초생성
 */
public class DocsPollingLongPolling {
    // 클라이언트에서 일반적으로 클라이언트에서 먼저 요청을 보내지 않아도 서버에서 클라이언트로 이벤트를 전달하는 기법

    // Polling : 주기적으로 클라이언트가 서버에 요청을 보내고 서버는 데이터나 이벤트가 없다면 텅 빈 값을, 클라이언트는 일정 주기를 기다린 후 다시 서버에 요청
    // Pooling 의 한계
    // 1. 클라이언트에서 대기하는 시간이 길다면, 실시간성이 떨어진다.
    // 2. 반대로 클라이언트에서 대기하는 시간이 짧다면, 서버에 부담이 된다
    // 3. 동기 non-blocking 방식과 유사하다. (BusyWait)

    // polling의 한계를 극복하기 위해 나온 기법이 Long polling
    // 1. 클라이언트가 먼저 요청을 보내는 부분은 동일하다.
    // 2. 서버는 전송할 이벤트 혹은 데이터가 있을때까지 대기한다.
    // 3. 이벤트 혹은 데이터가 준비되거나 timeout이 발생하면 클라이언트에 응답을 전달한다.
    // 4. 클라이언트는 응답을 받은 후 대기를 하지 않고 바로 long poll 요청을 전달한다.
    // 5. 쉽게 구현할 수 있고, 이벤트 및 데이터가 생길때마다 응답을 돌려주기 때문에 실시간성이 높다.

    // Long polling 기법의 한계
    // 1. long poll 요청과 응답 모두 하나의 독립적인 request, response이기 때문에 header를 모두 포함한다.
    // 2. 클라이언트와 서버 모두 TCP/IP 연결을 열고있는 상태로 대기. 따라서 한정된 connection pool과 관련된 리소스를 신경써야 한다.
    // 3. 클라이언트에게 제공해야할 이벤트가 queue에 쌓이면 각각의 이벤트를 단건으로 여러 개의 long poll 요청에 나눠서 전달한다.
    // 4. 브라우저 , gateway 등의 timeout을 고려하여 서버의 long poll 대기 시간을 정해야 한다.

    // polling은 요청을 너무 자주 보내고, 그로 인해 실시간성과 부하가 커진다. .Long polling은 자주 요청하지 않고 실제로 이벤트가 있을때만 보내지만 하나 하나마다 커다란 Http 이다.

    // 이 단점을 보완해서 사용 가능한게 Http Streaming 이다.
}