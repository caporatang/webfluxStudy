## Metadata mapping
- Entity 클래스에 어노테이션을 추가하여 데이터베이스와 관련된 설정들을 주입  
> - @id : primary key에 해당하는 필드에 적용
> - @Table : entity class에 적용. Table 이름 변경 가능
> - @Transient : 기본으로 모든 필드는 mapping 대상. @Transient가 붙은 필드 mppaing에서 제외.
> - @Column : entity의 property 필드에 적용. @Column이 붙은 필드에 대해서는 convention 기반 대신 Column에 주어진 name으로 적용
> - @Version : 낙관적 잠금(Optimistic Lock)에 이용. entity가 update 될때마다 자동으로 update
> - @PersistenceConstructor : 특정 constructor에 대해서 Object creation할 때 사용하게끔 지정. constructor의 argument 이름에 따라서 mapping

