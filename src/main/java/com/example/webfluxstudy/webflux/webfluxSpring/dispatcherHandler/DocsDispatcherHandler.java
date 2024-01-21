package com.example.webfluxstudy.webflux.webfluxSpring.dispatcherHandler;

/**
 * packageName : com.example.webfluxstudy.webflux.webfluxSpring.dispatcherHandler
 * fileName : DocsDispatcherHandler
 * author : taeil
 * date : 1/21/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/21/24        taeil                   최초생성
 */
public class DocsDispatcherHandler {
    // webHandler를 구현하고있음
    // handlerMappings, handlerAdapters, resultHandlers 로 구성되어 있다.

    // HandlerMapping, HandlerAdapter, HandlerResultHandler 를 List로 가지고있다.

    // DispatcherHandler의 처리 순서
    // 1. Reactor Netty에서 요청을 처리 해야한다.
    // 2. DispatcherHandler의 handle을 실행한다.
    // 3. DispathcerHandler는 HandlerMappingList를 순회하면서 매핑되는 hnadler를 찾고 handler를 반환한다.
    // 4. 반환받은 handler를 처리할수있는 HandlerAdapterList를 요청한다. -> HandlerAdapter를 반환한다.
    // 5. HandlerResult는 반환받은 adapter를 사용해서 handle을 실행하고, controller 등에서 처리하고, 처리한 결과를 HandlerResult를로 반환하다.
    // 6. HandlerResult는 HandlerAdapter에게 반환되고, HandlerResult는 DispatcherHandler에게 HandlerResult를 반환한다.
    // 7. HandlerResult를 HandlerResultHandlerList에서 처리할 수 있는 Handler를 요청하고, 처리 가능한 Handler를 반환한다.
    // 8. 반환받은 Handler를 handleResult 하게 되면, HandlerResultHandler 를 통해서 ReactorNetty에게 결과가 반환된다.

    // -> 서블릿 스택, 즉 우리가 아는 mvc를 기반으로 생각해보면 진행되는 순서는 비슷한거같다..~?
    // 이름같은게 좀 어려울뿐이다.

}