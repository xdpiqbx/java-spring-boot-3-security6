## [In process](https://youtu.be/KxqlJblhzfI?t=2927)

```text
spring-boot-starter-data-jpa
spring-boot-starter-security
spring-boot-starter-web
lombok
postgresql
```

Docker + postgresql container

Docker console `psql -U username` and enter password

`\l` - list of databases

`CREATE DATABASE jwt_security;`

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:2222/jwt_security
    username: name
    password: p@ssword
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

---

1. Create **User @Entity**
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {
  // user entity structure + implements all from UserDetails
}
```

2. @Repository
```java
public interface UserRepository extends JpaRepository<User, Integer> { 
  //  ... 
}
```

3. `JwtAuthenticationFilter`
```java
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain)
    throws ServletException, IOException {
    // ...
  }
}
```
