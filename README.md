# sch-bus-app/server

## Project Title
Android-based Bus Reservation Application

## Project Purpose
- To solve the inconvenience of depending solely on web platforms, this application offers a simple reservation system.
- Enhancements have been implemented to facilitate users in accessing bus schedules and bus pick-up/drop-off locations through an intuitive interface.
- To further enhance user convenience, the application has transitioned from the previous sole reliance on bank transfers for payments to integrating the KakaoPay payment method.

## Service Architecture
<image src="https://github.com/kimhamyong/schbus-app-server/assets/112596422/5dcde42a-45fc-4f8a-aef6-44654673d871" width =600>

## Environment Setting
```
-Language: JAVA
-Framework: Spring Boot
-DB: MySQL
-Build Version: JAVA 17
-Devleop Tool : Intelli J
-JDK : 19.0.2

dependencies {
   implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
   implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
   implementation 'org.springframework.boot:spring-boot-starter-web'
   compileOnly 'org.projectlombok:lombok'
   developmentOnly 'org.springframework.boot:spring-boot-devtools'
   runtimeOnly 'com.mysql:mysql-connector-j'
   annotationProcessor 'org.projectlombok:lombok'
   testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

## File Structure
```
📂server
┣ 📂pay
┃ ┣ 📜AmountVO
┃ ┣ 📜CardVO
┃ ┣ 📜KakaoPay
┃ ┣ 📜KakaoPayApprovalVO
┃ ┗ 📜KakaoPayReadyVO
┣ 📜Controller
┣ 📜Person
┣ 📜PersonDto
┣ 📜PersonRepository
┣ 📜Reserve
┣ 📜ReserveDto
┣ 📜ReserveRepository
┗ 📜SchbusApplication
```
