生成流水号工具类，格式

年月日 | 业务编码 | 编号

20181207 | 11 | 00000001


修改redis地址及端口号：application.yml

修改redis配置文件，打开aof设置。RDB方式不用关闭，两者互不干扰

```
appendonly yes  默认为no
```




GroboUtils-5-core.jar为junit多线程测试Jar包，使用方法参考CodeDemoApplicationTests