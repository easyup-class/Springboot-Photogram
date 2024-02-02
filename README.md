# 포토그램 - 인스타그램 클론 코딩

### mariadb - ddl update 시에 패킷 용량 초과 문제 해결
```text
SET GLOBAL max_allowed_packet=1000000000;
```

### 인메모리 DB로 테스트 하는 yml, maven

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    url: jdbc:h2:mem:test;MODE=MySQL
    driver-class-name: org.h2.Driver
    username: sa
    password: 
  h2:
    console:
      enabled: true
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: create # update <- 서버 데이터 유지 / create <- 사라짐 / none <- 아무것도 변경 못하게... / create-drop도 있는데 몰라도 된다고...
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
      
  servlet:
    multipart: # multipart 스타일로 사진을 받겠다.
      enabled: true # true <- 사진을 받겠다.
      max-file-size: 2MB # 사진 최대 용량은 2MB가 넘지 않도록 제한

  security:
    user:
      name: test
      password: 1234   

file: # 내가 만든 키값
  path: C:/workspace/upload/ # 업로드된 사진 저장할 공간(폴더)
```

### STS 툴에 세팅하기 - 플러그인 설정
- https://blog.naver.com/getinthere/222322821611

### 의존성

- Sring Boot DevTools
- Lombok
- Spring Data JPA
- MariaDB Driver
- Spring Security
- Spring Web
- oauth2-client

```xml
<!-- https://mvnrepository.com/artifact/org.apache.tomcat/tomcat-jasper -->
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>tomcat-jasper</artifactId>
	<version>11.0.0-M16</version>
</dependency>


<!--
https://mvnrepository.com/artifact/jakarta.servlet/jakarta.servlet-api -->
<dependency>
	<groupId>jakarta.servlet</groupId>
	<artifactId>jakarta.servlet-api</artifactId>
	<version>6.0.0</version>
	<scope>provided</scope>
</dependency>

<!--
https://mvnrepository.com/artifact/jakarta.servlet.jsp.jstl/jakarta.servlet.jsp.jstl-api -->
<dependency>
	<groupId>jakarta.servlet.jsp.jstl</groupId>
	<artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
	<version>3.0.0</version>
</dependency>

<!--
https://mvnrepository.com/artifact/org.glassfish.web/jakarta.servlet.jsp.jstl -->
<dependency>
	<groupId>org.glassfish.web</groupId>
	<artifactId>jakarta.servlet.jsp.jstl</artifactId>
	<version>3.0.0</version>
</dependency>


<!-- https://mvnrepository.com/artifact/org.qlrm/qlrm -->
<dependency>
	<groupId>org.qlrm</groupId>
	<artifactId>qlrm</artifactId>
	<version>4.0.1</version>
</dependency>


<!--
https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-aop -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>


<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-taglibs</artifactId>
</dependency>

```

### 데이터베이스

```sql
create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database photogram;
```

### yml 설정

```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/photogram
    username: cos
    password: cos1234
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: create
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
      
  servlet:
    multipart:
      enabled: true
      max-file-size: 2MB

  security:
    user:
      name: test
      password: 1234   

file:
  path: C:/src/springbootwork-sts/upload/
```

### 태그라이브러리

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
```
