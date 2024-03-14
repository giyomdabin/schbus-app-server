# sch-bus-app-back

sch-bus-app

1. Project Title
안드로이드 기반 스쿨버스 예매 애플리케이션

2. Project Period
2023.8.16~2023.9.15

3. 프로젝트 목적
- 기존에 웹 플랫폼밖에 존재하지 않아 불편했던 점을 해결하기 위해, 스쿨버스 예매 애플리케이션을 개발했습니다.
- 버스 시간 및 스쿨버스 승하차 장소를 직관적으로 확인할 수 있도록 개선했습니다.
- 사용자가 더 편리하게 결제할 수 있도록 계좌 이체만 가능했던 기존 결제 시스템에서 카카오페이 결제 방식으로 변경했습니다.

4. 멤버 구성
Front End - 정찬희, 백승규
Back End - 김하영, 장다빈

5. Project Overview

6. Service Architecture

6. Environment Setting
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

7. File Structure

📂server
┣ 📂pay
┃ ┣ 📜AmountVO
┃ ┗ 📜CardVO
┃ ┗ 📜KakaoPay
┃ ┗ 📜KakaoPayApprovalVO
┃ ┗ 📜KakaoPayReadyVO
┣ 📜Controller
┣ 📜Person
┣ 📜PersonDto
┣ 📜PersonRepository
┣ 📜Reserve
┗ 📜ReserveDto
┣ 📜ReserveRepository
┗ 📜SchbusApplication
