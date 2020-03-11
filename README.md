[![Build Status](https://travis-ci.com/mplanchant/resteasy-springboot-example.svg?branch=master)](https://travis-ci.com/mplanchant/resteasy-springboot-example)

Uses Spring Boot, Lombok, RESTEasy Spring Boot Starter (https://github.com/paypal/resteasy-spring-boot) and REST Assured.

```bash
curl -v 'http://localhost:8080/test-app/characters' --header 'Content-Type: text/plain' --data 'Donald Duck'
```

```bash
curl -v localhost:8080/test-app/characters
```

```bash
curl -v localhost:8080/test-app/characters/1
```