## R2dbcConverter
- R2dbcConverter는 row를 entity로 entity를 row 로 변환해주는 역할을 담당한다.
- EntityReader와 EntityWriter를 상속한다.
- 구현체로 MappingR2dbcConverter가 있다.

#### Entity <-> row , row <-> Entity 전략
1. custom converter로 mapping
2. Spring data의 object mapping
3. convention 기반의 mapping
4. metadata 기반의 mapping
> 이중 가장 많이 사용하는 2가지는 custom 과 object이고, convention과 metadata는 지원하는 개념의 mapping 전략이다.

#### Custom converter mapping
- configuration을 통해서 converter 들을 등록한다.
- Target 클래스를 지원하는 converter를 탐색한다
- 이를 위해서 두 개의 Converter가 필요하다. -> 읽기 쓰기를 하기 위해서
> 1. Row를 Target 클래스로 변환하는 Converter
> 2. Target 클래스를 OutboundRow로 변환하는 Converter

 

