## [In process](https://youtu.be/KxqlJblhzfI?t=4069)

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

4.
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
</dependency>
```

5. Create `public class JwtService {...}`

```java
@Service
public class JwtService {
  private static final String SECRET_KEY = "secret";
  public String extractUserName(String token) {...}
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){...}
  public String generateToken(UserDetails userDetails) {...}
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){...}
  public boolean isTokenValid(String token, UserDetails userDetails){...}
  private boolean isTokenExpired(String token) {...}
  private Date extractExpiration(String token) {...}
  private Claims extractAllClaims(String token){...}
  private Key getSignInKey() {...}
}
```