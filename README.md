# everyCommerce
    모두의 이커머스라는 뜻의 everyCommerce입니다. 쇼핑몰백앤드를 구현하였습니다.
    restAPI형 서버입니다. front개발보다는 서버에 집중하여 개발하였습니다. 
    기술적인 부분은 notion에서 보실 수 있습니다. 
    
### 기술스택:
    JPA DOCKER SPRINGBOOT MSA MYSQL 
    성능테스트: ngrinder
    로드밸런서: nginx
    컨테이너: docker
    로드밸런싱: spring gateway
    동시성처리: redis
    서비스 통신: kafka 
    

### 프로젝트 전체적인 구조 

![d](.\common\img\20231126_004944.png)
### 목표
    1. 모놀리식이 아닌 MSA로 서비스간 의존성을 낮춘 프로젝트 구조 생성
    2. 서버과부화를 해결할수 있는 프로젝트
    3. 동시성처리를 통해 

### 빌드 스텝
    첫실행시
    각 프로젝트 application.yml ->      ddl-auto: create로 변경해주세요.
    docker compose build
    docker compose up
    
    재 실행시
    cd commerce && gradle build -x test && cd ..
    cd gateway && gradle build -x test && cd ..
    cd order && gradle build -x test && cd ..다희 
    cd product && gradle build -x test && cd ..
    docker compose build
    docker compose up

### 프로젝트 진행 노션
    https://fabulous-guppy-d1a.notion.site/a24ef7f04f0648f389c6a32eadb8590b?pvs=4
### 브랜치 관리 전략
    Git Flow전략을 사용하였습니다


### 포트(로컬/도커)
    member 9090 / 9090 & 5000  
        - nginx 90
    product 9091 / 9091
        - nginx 80
    order 9092/7000
        - nginx 70
    gateway 9002 / 9002 
    kafka 9098 


