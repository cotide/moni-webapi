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
 
### 1.统一结果返回(fastJson),格式如下:
 
 
属性 | 描述  
---|--- 
code | 状态编码  
isSuccess | 请求是否成功 (指当前业务处理是否正确)  
msg | 消息 
data | 数据 

 
#### 1.1 没有结果数据-格式

```json
{
  "code": 200,
  "isSuccess": true, 
  "msg": ""
}
```

#### 1.2 有结果数据-格式
 
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
 
#### 1.3 异常数据-格式 
 

状态 | 描述 | JSON
---|---|---
401 | 非法权限 | {"code":401,"isSuccess":false,"msg":"异常信息"} 
500 | 服务器异常 | {"code":500,"isSuccess":false,"msg":"异常信息"}
510 | 请求参数异常 | {"code":510,"isSuccess":false,"msg":"异常信息"}
510 | 业务异常 | {"code":511,"isSuccess":false,"msg":"异常信息"}

 
### 2.Swagger的集成 (多版本) 

![Swagger的集成 (多版本) ](http://ww1.sinaimg.cn/large/7c2c6ab7gy1fv82d53swlj20i3067glq.jpg)
 
### 3.使用[sql2o-plus](https://github.com/cotide/sql2o-plus)
 
### 4.权限控制

![权限控制](http://ww1.sinaimg.cn/large/7c2c6ab7gy1fv82lnhijij20zk0clt9m.jpg)

需要权限访问的接口,请求时候需要带访问Token信息（Token鉴权使用[jwt](https://github.com/auth0/java-jwt)）

#### 4.1 @PowerFilter

控制器级别 - 标记该注解的Class需要权限才允许访问  
 
##### 4.1.1 使用

继承[AuthApiController](src/main/java/com/gold/moni/webapi/controller/base/AuthApiController.java)  
 

#### 4.2 @ActionPowerFilter  

Action级别 - 标记该注解的方法需要权限才允许访问

##### 4.2.1 使用

对需要权限的方法标记@ActionPowerFilter注解 

```java
@ActionPowerFilter 
@RequestMapping(value = "get",method = RequestMethod.GET)
public void get(){}
```


### 5.自定义格式

#### 5.1 @IgnoreRequestFilter 

忽略全局格式,使用自定义格式

```java
@ApiOperation(value = "获取自定义格式")
@IgnoreRequestFilter
@RequestMapping(value = "getMyResult",method = RequestMethod.GET)
public String getMyResult(){}
```


#### 5.2 @Download 

文件下载

```java
@ApiOperation(value = "Excel下载")
@Download
@RequestMapping(value = "downloadExcel",method = RequestMethod.GET)
public void downloadExcel(
        HttpServletRequest request,
        HttpServletResponse response)
        throws IOException,
        IllegalAccessException,
        InstantiationException,
        ClassNotFoundException {}
```


### 6 图片二维码合并输出 (缓存)

```code
@Download
@Cacheable(value = "qr_getGithubQR",key = "#userName")
@Scheduled(fixedRate = ONE_DAY )
@ApiOperation(value = "获取Github二维码")
@RequestMapping(
        value = "getGithubQR",
        method = RequestMethod.GET,
        produces = MediaType.IMAGE_PNG_VALUE
)
public byte[] getGithubQR(String userName)
        throws IOException, WriterException {

    BufferedImage in = ImageIO.read(getClass().getResourceAsStream("/static/img/rq_bg.png"));
    if(in==null)
    {
        throw new NullPointerException("未找到背景图");
    }
    BufferedImage qrImg = QRCodeUtil.toBufferedImage("https://github.com/"+userName,240,240);
    BufferedImage outPut = QRCodeUtil.mergeImage(in,qrImg,200,170);
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    try {
        ImageIO.write(outPut, "png", output);
        output.flush();
        return output.toByteArray();
    }finally {
        output.close();
    }
}

```

![图片二维码合并输出 (缓存)](http://ww1.sinaimg.cn/large/7c2c6ab7gy1fw37s7urcqj20fj0bt74v.jpg)

## 资源

- [示例数据库脚本](https://github.com/cotide/moni-webapi/wiki/%E7%A4%BA%E4%BE%8B%E6%95%B0%E6%8D%AE%E5%BA%93%E8%84%9A%E6%9C%AC)

 