# service
前台服务

### 1. 简介
个人项目,主要用于个人笔记使用.

配合 [service-web](https://github.com/li88qq/service-web) 使用.

线上demo地址: 
[https://www.li88qq.com](https://www.li88qq.com).

### 2. 开发相关
1. 整体框架:Springboot 2.5.0
2. 数据库:mysql 8.0,redis
3. 持久层框架: [fastquery](https://github.com/xixifeng/fastquery)

### 3. 功能
1. 常用开发辅助工具.包括json转换,时间戳转换,二维码显示,自定义导航等.
2. 笔记.使用markdown记录文章.
3. 登录记录和操作记录查询管理.

### 4. 开发阶段配置
1. application.yml 启用配置为:dev.
2. hikari.xml中配置mysql数据源.
3. application-dev.yml中配置redis数据源.
4. 数据库配置.数据库使用mysql,默认为8.0,如果是其他版本,需要调整pom.xml中配置.
5. 初始化数据库.建表sql:table.sql
