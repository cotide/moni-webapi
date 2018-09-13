## moni-webapi 

[![Build Status](https://travis-ci.org/cotide/moni-webapi.svg?branch=master)](https://travis-ci.org/cotide/moni-webapi)
 
 基于SpringBoot的WebApi示例
 
 ## 访问地址
 
 > http://localhost:9200/swagger-ui.html
 
 ## 引用
 
 - [spring-boot](https://github.com/spring-projects/spring-boot)
 - [swagger2](https://github.com/springfox/springfox) 
 - [druid](https://github.com/alibaba/druid)
 - [sql2o-plus](https://github.com/cotide/sql2o-plus)
 - [log4j2](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-project/spring-boot-starters/spring-boot-starter-log4j2)
 - [fastjson](https://github.com/alibaba/fastjson) 
 - [jwt](https://github.com/auth0/java-jwt)

 > 访问 [pom.xml](pom.xml)
 
 
## 特点
 
### 统一结果返回,格式如下:
 
 
属性 | 描述  
---|--- 
code | 状态编码  
isSuccess | 请求是否成功 (指当前业务处理是否正确)  
msg | 消息 
data | 数据 

 
#### 没有结果数据-格式

```json
{
  "code": 200,
  "isSuccess": true, 
  "msg": ""
}
```

#### 有结果数据-格式
 
```json
{
  "code": 200,
  "data": {
    "createTime": "2018-09-05 18:03:15",
    "login": 1000,
    "userId": 29,
    "userName": "Hello World"
  },
  "isSuccess": true,
  "msg": ""
}
```
 
#### 异常数据-格式 
 

状态 | 描述 | JSON
---|---|---
401 | 非法权限 | {"code":401,"isSuccess":false,"msg":"异常信息"} 
500 | 服务器异常 | {"code":500,"isSuccess":false,"msg":"异常信息"}
510 | 请求参数异常 | {"code":510,"isSuccess":false,"msg":"异常信息"}
510 | 业务异常 | {"code":511,"isSuccess":false,"msg":"异常信息"}

 
### Swagger的集成 (多版本) 

### 统一JSON处理 (fastJson)

### 使用[sql2o-plus](https://github.com/cotide/sql2o-plus)
 
### 权限控制

#### Controller级别 - [@PowerFilter](src/main/java/com/gold/moni/webapi/filter/jwt/attr/PowerFilter.java)


##### 使用

继承[AuthApiController](src/main/java/com/gold/moni/webapi/controller/base/AuthApiController.java) 需要Token才允许访问

   

#### Action级别 - [@ActionPowerFilter](src/main/java/com/gold/moni/webapi/filter/jwt/attr/ActionPowerFilter.java)


##### 使用

使用@ActionPowerFilter注解标记到Action方法上

```java
@ActionPowerFilter 
@RequestMapping(value = "get",method = RequestMethod.GET)
public void get(){};
```


## 资源

- [示例数据库脚本](https://github.com/cotide/moni-webapi/wiki/%E7%A4%BA%E4%BE%8B%E6%95%B0%E6%8D%AE%E5%BA%93%E8%84%9A%E6%9C%AC)

 