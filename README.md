# android-github-compose

## step1

- [x] 네트워크와 관련된 의존성
- [x] GithubService 와 Repository
- [x] Service및 Repository DI Container
- [x] Activity 에서 Github Service 요청하는 테스트 로그

## step2

- [x] step1 리뷰 반영 (테스트 코드 변경)
- [x] Github Repository Column 아이템 UI 구현
- [x] Item LazyColumn 연결
- [x] ViewModel 데이터 추가 및 Factory 연결

## step3

- [x] 로딩 상태 추가 및 업데이트 구현
- [x] Repository loadRepository 함수에 따른 ViewModel 테스트 추가
- [x] 빈 목록 UI Section 구현 및 연결
- [x] 빈 목록 UI Section 테스트 코드 추가
- [x] 오류 상태 추가 및 error handling 추가
- [x] 오류 발생한 경우 재시도 가능한 스낵바 추가

## step4

- [x] Github ResponseEntity에 `stargazers_count`을 추가한다.
- [x] Github Repo 아이템에 Star 부분을 추가한다.
- [ ] Star를 UI에 적용한다.
- [ ] Star 개수가 50개 이상이면 HOT 텍스트 노출하는 로직 추가
- [ ] HOT 텍스트 아이템에 추가
- [ ] HOT 적용 및 테스트 코드 추가
