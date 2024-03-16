package com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.converter;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import java.util.List;

/**
 * packageName : com.example.springdatareactive.r2dbc.r2dbcEntityTemplate.converter
 * fileName : R2dbcConfig
 * author : taeil
 * date : 3/17/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 3/17/24        taeil                   최초생성
 */
@Configuration
public class R2dbcConfig extends AbstractR2dbcConfiguration {
    // AbstractR2dbcConfiguration 을 상속하는 Configuration 생성
    // AbstractR2dbcConfiguration의 getCustomConverters에 custom converter들을 List형태로 제공할 수 있다.

    @Override
    protected List<Object> getCustomConverters() {
        // configuration에 등록을 하기 때문에 Person에 대해서 읽기, 쓰기 하는 경우에,
        // 여기서 넘기는 converter를 우선적으로 사용하게 된다.
        return List.of(
                new PersonReadConverter(),
                new PersonWriteConverter()
        );
    }

    @Override
    public ConnectionFactory connectionFactory() {
        return null;
    }
}