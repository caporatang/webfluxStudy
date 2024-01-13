package com.example.webfluxstudy.webflux.webHandler.multipart;

/**
 * packageName : com.example.webfluxstudy.webflux.webHandler.multipart
 * fileName : DocsMultipartData
 * author : taeil
 * date : 1/13/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/13/24        taeil                   최초생성
 */
public class DocsMultipartData {
    // Part 인터페이스를 구현한 FormFieldPart와 FilePart로 구분
    // -> Part 인터페이스는 name, headers, content 로 구성이 되어있음
    // -> content는 Flux<DataBuffer> 의 형태를 갖고있다.

    // FormFieldPart는 plain text 필드에 해당. -> value()를 통해서 값에 접근 가능

    // FilePart는 file 필드에 해당한다.
    // filename() 으로 파일명에 접근하고, transferTo로 로컬에 File 필드의 내용을 전달한다.
}