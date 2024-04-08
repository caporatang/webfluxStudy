package com.example.springdatareactive.r2dbc.metedataMapping;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * packageName : com.example.springdatareactive.r2dbc.metedataMapping
 * fileName : PersonWithMetadata
 * author : taeil
 * date : 4/8/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 4/8/24        taeil                   최초생성
 */
@ToString
@Table("person")
public class PersonWithMetadata {
    // id 필드를 @Id를 붙여서 primarykey로 지정
    // fullName에 @Column("name") 을 이용해서 row의 name 필드와 mapping
    // score에 @Transient로 mapping 대상에서 제외
    // id, fullName, age, gender를 인자로 받는 constructor를 @PersistenceCreator로 설정
    // version에 @Version을 추가

    @Id
    private final Long id;
    @Column("name")
    private final String fullName;
    private final int age;
    private  String gender;

    @Getter
    @Setter
    @Version
    private Long version;

    @Setter
    @Transient // score는 디비에서 값을 불러오거나 저장하지 않겠다.
    private Integer score;

    public PersonWithMetadata(Long id, String fullName, int age, String gender) {
        this(id, fullName, age, gender, null);
    }

    public PersonWithMetadata(Long id, String fullName, int age, String gender, Long version) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.version = version;
    }
}