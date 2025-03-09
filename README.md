# android-github-compose

# step1 Data layer

## NEXTSTEP 조직의 저장소 목록을 가져오는 Client 구현

- [x] Retrofit 라이브러리를 이용해서 data 패키지 이하에 네트워크 통신부 구현
- [x] GET https://api.github.com/orgs/next-step/repos 호출
    - full_name, description 필드만 가져오기
- [x] 수동 DI 구현
- [x] LOG 를 이용해, 서버 데이터가 잘 로드 되는지 확인

# step2 UI layer

## NEXTSTEP 조직의 저장소 목록을 선형 리스트 노출

- [x] UI 구현
  - [x] item
  - [x] 리스트
  - [x] Preview
- [x] ViewModel 연결
  - [x] 비지니스 로직 구현
  - [x] UI 연결
  - [x] ViewModelFactory
