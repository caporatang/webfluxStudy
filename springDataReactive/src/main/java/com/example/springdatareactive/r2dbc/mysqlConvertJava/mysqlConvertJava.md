## propery mapping 타입 변환
- Row로부터 get을 통해서 특정 Java 클래스로 변환

### MySQLRow
- Row의 구현체인 MySqlRow는 Codecs를 포함
- 해당 codecs를 이용하여 column의 정보, 값을 전달하고 type을 갖는 객체를 반환
> - FieldValue : column의 실제 값에 해당하는 ByteBuf 혹은 List<ByteBuf> 포함
> - MySqlColumnDescriptor : MySqlType, column name, nullable, size 등의 Column meta 정보 포함

### MySQL DefaultCodecs (1)
- MySQL defaultCodecs에는 기본적으로 codec들을 포함
- 각각의 codec은 canDecode, decode를 구현
> - canDecode: columnMetadata를 기반으로 target(특정 Java 타입) 으로 변경 가능한지 여부 반환
> - decode : 주어진 column의 value를 특정 타입의 객체로 반환

### MySQL DefaultCodecs (2)
- 기본적으로 26개의 Codec을 지원한다.
- Byte, Short, Integer, Long, BigInteger
- BigDecimal, Float, Double
- Boolean, BitSet
- ZonedDateTime, LocalDateTime, Instant, OffsetDateTime
- LocalDate... 등등...

### MySQL Data Mapping
![MySQL_Data_Mapping(1)](img/MySQL_Data_Mapping(1).png)  
![MySQL_Data_Mapping(2)](img/MySQL_Data_Mapping(2).png)  
![MySQL_Data_Mapping(3)](img/MySQL_Data_Mapping(3).png)  