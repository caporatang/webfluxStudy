## R2dbcEntityTemplate
- R2dbcEntityTemplate은 Spring data r2dbc의 추상화 클래스
- SQL 쿼리들을 문자열 형태로 넘기거나 결과를 직접 처리하지 않아도 메서도 체이닝을 통해서 쿼리를 수행하고 결과를 entity 객체로 받을 수 있다.
- R2dbcEntityOperations를 구현한다.

### R2dbcEntityTemplate 생성
- ConnectionFactory를 제공하거나, R2dbcDialect, R2dbcConverter를 제공하여 constructor로 생성가능
> R2dbcDialect : R2dbc 버전의 Dialect 확장
>> - bindMarkerFactory, converter 등 각각 데이터베이스에 특화된 정보를 제공한다.
>> - 각각의 vender 에서 제공하는 Dialect를 지원하며, H2, MySql, Oracle, Postgres, SqlServer 를 제공한다.

### R2dbcEntityTemplate bean
- R2dbcDataAutoConfiguration을 통해서 DatabaseClient, R2dbcDialect, MappingR2dbcConverter를 주입
> R2dbcDataAutoConfiguration 클래스 내부에 Template를 만들면서 converter, client를 주입 받아서 생성한다.

### R2dbcEntityOperations 
- DatabaseClient와 R2dbcConverter를 제공한다.
> - DatabaseClient : ConnectionFactory를 wrapping하여 결과를 Map이나 integer로 반환한다.
> - R2dbcConverter : 주어진 Row를 Entity로 만드는 converter
- R2dbcEntityTemplate에서는 이 DatabaseClient와 R2dbcConverter로 쿼리를 실행하고 결과를 entity로 반환한다.

### DatabaseClient 
- 내부에 포함된 ConnectionFactory에 접근 가능
- sql 메소드를 통해서 GenericExecuteSpec을 반환한다.
- GenericExecuteSpec은 bind를 통해서 parameter를 sql에 추가한다.
- fetch를 통해서 FetchSpec을 반환한다.
> - FetchSpec? 
> > - FetchSpec은 RowFetchSpec과 UpdatetdRwosFetchSpec을 상속.
> > - RowsFetchSpec : one, first, all 메서드 제공
> > > 1. one : 없거나 혹은 하나의 결과를 Mono로 제공. 결과가 하나보다 많다면 에러 반환 
> > > 2. first : 첫 번째 결과를 Mono로 제공. 없다면 빈 값을 반환
> > > 3. all : 모든 결과를 Flux로 제공
> > - UpdatetdRwosFetchSpec : 쿼리의 영향을 받은 row 수를 Mono로 제공
