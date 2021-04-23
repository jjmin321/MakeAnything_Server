# MakeAnything
🛍 대구소프트웨어고등학교 학생들이 사용할 수 있는 교내 마켓 서비스 (재능 물품, 중고 물품, 요청 물품)

## 기술 Stack
|                      | Web     | Server        | 
|:--------------------:|:---------------:|:------------------:|
| Developer | 제정민 | 제정민       | 
| Develop Language | TypeScript | Java | 
| FrameWork | NextJS | Spring Boot |
| Develop Tool     | Visual Studio Code  | InteliJ | 

## 소개

대구소프트웨어고등학교 학생들이 함께 사용할 수 있는 교내 마켓 서비스입니다.

서비스는 3가지로 각각 관심사가 분리되어 개발됩니다
- Item (물품) 
- User (유저)
- Common (공용)

### 물품  
물품은 재능 물품 (디자인 외주 등), 중고 물품 (사용하지 않는 책 등), 요청 물품 (웹 퍼블리싱 요청 등)이 있습니다<br>
물품에 관련된 제어 계층과 그에 따른 비즈니스 로직 계층, 즉 물품에 특화된 기능이 개발됩니다<br>

```
item
    ㄴ controller - 클라이언트의 요구사항
    ㄴ service - 요구사항의 비즈니스 로직
    ㄴ domain - 데이터베이스 관련 
```

### 유저
유저는 교내 스마트 스쿨 플랫폼인 도담도담 API를 사용해 기본적인 유저의 정보를 관리합니다<br>
유저에 관련된 제어 계층과 그에 따른 비즈니스 로직 계층, 즉 유저에 특화된 기능이 개발됩니다<br>

```
user
    ㄴ controller - 클라이언트의 요구사항
    ㄴ service - 요구사항의 비즈니스 로직
    ㄴ domain - 데이터베이스 관련 
```

### 공용 
공용은 물품, 유저 서비스에서 공용으로 사용하는 모듈들이 포함되어 있습니다<br>
비즈니스 계층이 아닌 에러 처리, 보안 등 공통적인 저수준 기능이 개발됩니다<br>

```
common
    ㄴ annotation - 직접 정의하여 사용하는 어노테이션
    ㄴ aspect - filter, interceptor와 달리 특정 비즈니스 로직의 공통 관심사항을 정의 
    ㄴ configuration - IOC & DI를 위한 Bean 생성, Configurer 구현체
    ㄴ enums - 코드 단순화, 가독성 등을 위한 열거형 타입 
    ㄴ exception - 정확한 에러 생성을 위한 사용자 정의 에러
    ㄴ filter - 모든 컨트롤러에 대한 요청을 필터링 
    ㄴ handler - 정확한 에러 처리를 위한 핸들러
    ㄴ interceptor - 특정 컨트롤러에 대한 요청을 검사 
    ㄴ lib - 비즈니스 로직과 분리된 재사용, 가독성을 위한 모듈
    ㄴ retrofit - 교내 도담도담 서비스와 통신을 위한 모듈
    ㄴ vo - 초기화 후 Getter을 통해 값만을 사용하기 위한 오브젝트
```


### 계층 구조 
기본적인 구조는 3계층(Controller, Service, Repository)으로 이루어집니다<br>
HTTP Request의 요청이 들어올 때 Filter 계층에서 정제 역할(CORS 처리)을 합니다<br>
HTTP Request에 따라 특정 처리가 필요할 때 Interceptor 계층(유저 인증)이 추가될 수 있습니다<br>
특정 비즈니스 로직에서 공통 관심사항이 되는 코드가 필요할 때 @Before, @Around, @After Advice 계층(유저 확인)이 추가될 수 있습니다<br>
DTO를 사용하여 계층 간 데이터를 교환하고, VO를 사용하여 Response 등을 생성합니다.

![image](https://user-images.githubusercontent.com/52072077/115810464-ea363400-a428-11eb-84ad-c6fb0e456166.png)

# 2021년 5월 말 배포 예정으로 개발 중입니다 