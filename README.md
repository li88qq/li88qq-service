# 个人服务平台

## 1. 简介
- 该项目为个人项目, 主要用于搭建一个提供日常工作中使用到的工具, 一个可以保存个人文章, 查询一些技术文档信息的平台.
- 该项目当前为2.0整合版本

## 2. 应用框架
- spring cloud | gateway | nacos 
- redis
- mysql
- mybatis

## 3. 模块介绍
### admin
- 管理后台

### bean
- 公共包, 包括实体类, 表结构统一配置和异常处理, 提供web使用的工具组件

### db
- 在mybatis上封装. 
- 提供通用的BaseMapper, 进行insert, update, delete方法. 
- 封装分页功能
- 自定义动态查询条件功能

### gateway
- 统一网关

### login
- 统一登录管理功能

### main
- 前台(用户)使用平台服务

### parent
- 父项目,依赖管理

### common
- 公共依赖包

### 4. LICENSE
[MIT](https://en.wikipedia.org/wiki/MIT_License)

### 5. Thanks for free JetBrains Open Source license
![JetBrains](https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg)

[**JetBrains**](https://jb.gg/OpenSourceSupport/)
