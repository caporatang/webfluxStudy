package com.example.webfluxstudy.webflux.securityReactive;

/**
 * packageName : com.example.webfluxstudy.webflux.securityReactive
 * fileName : DocsSecurityReactive
 * author : taeil
 * date : 1/31/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/31/24        taeil                   최초생성
 */
public class DocsSecurityReactive {
    // 기존 Servlet stack 에서의 security는 Servlet filter를 사용한다
    // Servlet filter에 DelegatingFilterProxy를 추가하고
    // DelegatingFilterProxy 는 SecuriyFilterChain들을 호출한다.
    // filterChain 내에서는 bean을 사용하기 힘들지만, securiyFilterChain은 spring context를 갖기 때문에 bean에 접근 가능하다.
    // -> security filter Security Context holder 를 이용해서 인증 인가 정보를 쭉 이어받았다 -> 이것이 가능한 이유는 Thread Local 즉, 하나의 스레드에서 전부 동작되기 때문이다.


    // reactive security 에서는 SecurityWebFilterChain을 사용한다.
    // reactive stack 에서는 HttpWebHandlerAdapter의 WebFilter를 사용한다.
    // WebFilter를 구현한 WebFilterChainProxy는 servlet stack의 DelegatingFilterProxy와 동일한 역할을 수행한다

    // ReactiveSecurityContextHolder
    // SecurityContextHolder 와 비슷하지만 THreadLocal 대신 context로 SecurityContext를 제공한다. -> context를 사용하면 파이프라인 내부에서 어디서든 request에 접근할 수 있다.
    // 제공 메서드는
    // 1. getContext : SecurityContext를 Mono 형태로 제공한다.
    // 2. clearContext : SecurityContext를 clear 한다.
    // 3. withSecurityContext : securityContext를 Mono로 받고 이를 포함하는 Reactor context 반환.
    // 4. withAuthentication : security Authentication을 받고 SecurityContext를 받아서 이를 포함하는 Reactor context 변환
}