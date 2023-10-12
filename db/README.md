## db

### 简介
- 对mybatis的二次开发
- 提供基本的insert,update操作
- 提供动态条件查询功能
- 提供分页查询功能
- 多数据源,切换(待实现)

### 使用方法
- 动态查询
1. @Select,@Update,@Delete 语句中,需包含一个且仅一个 ***:where*** 占位符.
2. 查询语句需包含 ***@Condition*** 注解, 该注解中的字段使用 ***name = :动态字段*** 或 ***name = :{对象.动态字段}*** 表示.
3. ***@Condition*** 中,全部使用的连接字为: ***and*** ,且不需要手动拼接.
4. ~~如果需要对一些字段格式化处理,比如字符串like,LocalDate/LocalDateTime转时间戳,可以在条件对象前加 ***@Format*** 注解,
且在对象字段上使用 ***@Like*** ,***@TimeMin*** 等注解.~~
5. 格式化方法:在@Conditon注解中,使用f枚举来声明格式化.

- 分页查询
1. 查询参数需包含 ***Pageable*** 对象.
2. 返回对象为: 方法返回对象类型为: ***Page&lt;T&gt;*** .
3. 默认查询统计字段为: ***id*** ,如果是其他字段,或者是多表查询,需使用 ***@PageId*** 注解指定该统计字段.
4. 可以使用 ***Page.build()*** 方法转换为前端使用的对象.
5. 分页需使用@PageId注解,用于aop拦截并处理统计数量,和对数据封装.

### 原理
- 动态查询
1. mybatis留下了自定义拦截器Interceptor，可以进行对sql、参数等封装。见MybatisInterceptor。
2. 分页查询时，用了aop切面，以及ThreadLocal处理。见PageIdThreadLocal、PageIdAspect。
3. 因为mybatis本身是使用xml描述的，动态语句也会组合成多个条件节点。见SqlNode、ConditionChainManager。

- 切换数据源
1. spring boot自带的数据源抽象类：AbstractRoutingDataSource，需实现determineCurrentLookupKey方法。
2. 当需要切换数据前，可以使用ThreadLocal动态处理当前数据源，并在determineCurrentLookupKey方法中返回。

- 事务处理
1. 如果非分布式数据源或多个数据源，可以使用spring boot自带的@Transactional注解处理。
2. 如果分布式数据源或多个数据源，需要使用分布式事务。如Seata。