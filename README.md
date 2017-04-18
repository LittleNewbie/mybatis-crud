# portal
## version:V1.0 实现功能
    1、对单条数据的查询、插入、更新、删除.
	2、批量操作数据：批量插入，根据条件删除，根据条件更新指定的列名-字段值.
	3、高级查询：可设置查询列，查询条件，排序，分页.
## version:V1.0.1 版本说明
    1、mysql与oracle隔离;
	2、支持oracle批量插入;
	3、增加对基本数据类型的处理;
	4、增加JdbcTemplate generalDao;
	5、代码优化.
## 注意事项
 **目前支持的数据类型有:Primitive String Boolean Number Date,其他数据类型暂不能有效识别/转化,详见FieldReflectUtil;**
 **1.MySQL支持所有操作,Oracle暂不支持分页查询;**
 **2.java-SQL映射默认为驼峰与下划线,如未按照此约定,需使用@Table和@Column注明SQL的表名/列名;**
 **3.使用selectByPrimaryKey()等与主键相关的方法时,需使用@Id注明主键字段;**
 **4.非持久化字段需使用@Transient注明;**
 **5.更多关于持久化的方式/细节,参见PersistentUtil;**
 **6.对于枚举类型的处理，以枚举ordinal()值，在数据库存取.**
