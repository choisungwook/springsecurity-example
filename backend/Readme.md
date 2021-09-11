# 개요
* 인메모리 사용자 생성
> 인메모리는 데이터베이스에 사용자를 관리하지 않고 메모리로 관리되는 유저관리방법이다. 프로세스가 종료되면 사용자는 삭제된다.

<br>

# 구현
* WebSecurityConfigurerAdapter을 상속하여 void configure(AuthenticationManagerBuilder auth)을 오버라이딩해서 구현할 수 있다.
* 비밀번호를 평문으로 저장하기 위해서 {noop} prefix가 필요하다.
```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("aaa").password("{noop}password").roles("ADMIN");
    auth.inMemoryAuthentication().withUser("bbb").password("{noop}password").roles("ADMIN");
    auth.inMemoryAuthentication().withUser("ccc").password("{noop}password").roles("ADMIN");
}
```

<br>

# 로그인
* aaa / password
* bbb / password
* ccc / password

<br>


# 참고자료
* https://docs.spring.io/spring-security/site/docs/current/reference/html5/#authentication-password-storage-dpe-format