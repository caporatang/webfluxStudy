package com.example.webfluxstudy.webflux.codec;

/**
 * packageName : com.example.webfluxstudy.webflux.codec
 * fileName : HowMakeServerWebExchange
 * author : taeil
 * date : 1/10/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 1/10/24        taeil                   최초생성
 */
public class HowMakeServerWebExchange {
    // ServerWebExchange 만드는 과정
    // HttpWebHandlerAdapter는 ServerWebExchange를 만들면서 CodecConfigurer를 전달한다. -> CodecConfigurer 로 formData와 multipartData mono를 초기화한다.
    // -> HttpWebHandlerAdapter는 ServerWebExchange 에다가 본인이 가지고 있는 다양한 정보를 넘긴다.
    // -> DefaultServerWebExchange가 생성이 될때 formDataMono를 준비한다.

    // formDataMono 준비
    // configurer의 headers 중에 application/x-www-form-urlencoded를 지원하는 reader 탐색 -> 없다면 IllegalStateException throw
    // 해당 reader 에 request를 넘겨주고 읽어들이는 mono를 준비
    // cache : 최초 subscribe에 대해서만 onNext, onComplete 등의 이벤트를 전달하고 이후 subscribe에 대해서는 실제로 publisher가 수행되지 않고 cache된 이벤트를 전달한다.


    // -------- typed readers --------
    // default typed readers (formDataMono에서 탐색하는 reader) - 서버와 클라이언트 둘 다
    // ByteArrayDecoder : DataBuffer를 byte array로 decode
    // ByteBufferDecoder : DataBuffer를 java.nio.ByteBuffer 로 decode
    // DataBufferDecoder : DataBuffer를 DataBuffer로 decode
    // NettyByteBufDecoder : DataBuffer 를 io.netty.buffer.ByteBuf로 decode
    // ResourceDecoder : DataBuffer를 Spring의 Resource로 decode
    // StringDecoder : DataBuffer를 String으로 decode
    // ProtobufDecoder : DataBuffer를 com.google.protobuf.Message로 decode. -> google protobuf 라이브러리가 있을때만 등록한다.

    // default server typed readers -> 서버에서만 등록되는 readers
    // FormHttpMessageReader : MediaType이 application/x-www-form-urlencoded인 경우, form MultiValueMap<String, String> 형태로 read
    // DefaultPartHttpMessageReader : MediaType이 multipart/form-data인 경우, Part를 stream 형태로 read
    // MultipartHttpMessageReader : MediaType이 multipart/form-data인 경우, MultiValueMap 형태로 read

    // -------- typed writers --------
    // default typed writers
    // ByteArrayEncoder : byte array를 DataBuffer로 encode
    // ByteBufferEncoder : java.nio.ByteBuffer를 DataBuffer로 encode
    // DataBufferEncoder : DataBuffer를 DataBuffer로 encode
    // NettyByteBufEncoder : io.netty.buffer.ByteBuf를 DataBuffer로 encode
    // ResourceHttpMEssageWriter : Spring의 Resource를 DataBUffer로 encode
    // CharSequenceEncoder : CharSequence를 DataBuffer로 encode
    // ProtobufHttpMessageWriter : com.google.protobuf.Message를 DataBuffer로 encode

    // default server typed writers
    // PartHttpMessageWriter : Part들을 DataBuffer 형태로 write 한다.


    // -------- object readers --------
    // Jackson2JsonDecodker : jackson 라이브러리를 사용해서 json 형태의 DataBuffer를 객체로 decode. jackson 라이브러리가 있을때만 등록
    // KotlinSerializationJsonDecoder : kotlinx.serialization 라이브러리를 사용해서 json 형태의 DataBuffer를 객체로 decode. kotlinx.serialization 라이브러리가 있을때만 등록
    // Jackson2SmileDecoder : jackson 라이브러리를 사용해서 smile 형태의 DataBuffer를 객체로 deocde. jackson-smile 라이브러리가 있을때만 등록
    // Jaxb2XmlDecoder : jaxb를 사용하여 xml 형태의 DataBuffer를 객체로 decode. jaxb라이브러리가 있을때만 등록한다.
    // -> writer도 reader와 반대로 제공한다.
    // default server object writers
    // ServerSentEventHttpMessageWriter : 객체를 ServerSentEvent 형태로 encode하여 write
}