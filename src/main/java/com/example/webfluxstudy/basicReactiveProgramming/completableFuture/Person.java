package com.example.webfluxstudy.basicReactiveProgramming.completableFuture;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * packageName : com.example.webfluxstudy.completableFuture
 * fileName : Person
 * author : taeil
 * date : 2023/10/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/10/02        taeil                   최초생성
 */
@RequiredArgsConstructor
public class Person {
    // Method reference 예제 클래스
    // Method reference -> :: 연산자를 이용해서 함수에 대한 참조를 간결하게 표현
    // 1. method reference
    // 2. static method reference
    // 3. instance method reference
    // 4. constructor method reference

    @Getter
    private final String name;

    public Boolean compareTo(Person o) {
        return o.name.compareTo(name) > 0;
    }

    public static void print(String name) {
        System.out.println(name);
    }

    public static void main(String[] args) {
        var target = new Person("f");
        Consumer<String> staticPrint = Person::print;

        Stream.of("a", "b", "g", "h")
                .map(Person::new) // constructor reference
                .filter(target::compareTo) // method reference
                .map(Person::getName) // instance method reference
                .forEach(staticPrint); // static method reference

    }
}

