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

서비스는 3가지로 분리되어 각각 개발됩니다
- Item (물품) 
- User (유저)
- Common (공용)

### 물품  
물품은 재능 물품 (디자인 외주 등), 중고 물품 (사용하지 않는 책 등), 요청 물품 (웹 퍼블리싱 요청 등)이 있습니다<br>
물품에 관련된 제어 계층과 그에 따른 비즈니스 로직 계층, 즉 물품에 특화된 기능이 개발됩니다<br>
Controller, Service, Domain 등이 포함됩니다

### 유저
유저는 교내 스마트 스쿨 플랫폼인 도담도담 API를 사용해 기본적인 유저의 정보를 관리합니다<br>
유저에 관련된 제어 계층과 그에 따른 비즈니스 로직 계층, 즉 유저에 특화된 기능이 개발됩니다<br>
Controller, Service, Domain 등이 포함됩니다

### 공용 
공용은 물품, 유저 서비스에서 공용으로 사용하는 모듈들이 포함되어 있습니다<br>
비즈니스 계층이 아닌 에러 처리, 보안 등 공통적인 저수준 기능이 개발됩니다<br>
사용자 정의 에러, Response VO, 라이브러리 등이 포함됩니다

# 2021년 5월 말 배포 예정으로 개발 중입니다 