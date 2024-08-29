# android-github-compose

## 🚀 1단계 - GitHub(데이터 레이어)

- [v] 권한 및 의존성 추가
- [v] NEXTSTEP 조직의 저장소 목록을 가져오는 Client를 구현
- [v] 네트워크 요청으로 저장소 목록을 가져오는 기능 구현
- [v] 수동 DI를 구현한다. (Hilt와 같은 별도의 DI 라이브러리를 활용하지 않는다)

## 🚀 2단계 - GitHub(UI 레이어)

- [v] Repositories 화면 구현
  - [v] `GithubRepoScreen` 생성
  - [v] `GithubRepoScreen` UI test 구현
  - [v] `GithubRepoScreen` 컴퍼넌트 상세 구현
- [v] Repositories 화면에 필요한 데이터를 전달하는 ViewModel 구현

## 🚀 3단계 - GitHub(UI 상태)
- [v] PR 리뷰 반영
  - [v] Divider 추가
  - [v] organization 선언 수정
  - [v] `GithubRepoScreenTest`에 `study` package 이동
- [v] Repositories 결과에 따른 각 상태(로딩, 에러, 빈 목록, 성공)에 따른 UiState 생성
  - [v] `GithubRepoUiState` 생성
  - [v] `GithubRepoUiState`에 각 상태에 따른 데이터 추가
- [] Repositories 결과에 따른 각 상태(로딩, 에러, 빈 목록, 성공)에 따른 UI 처리
  - [v] 각 상태에 따른 UI test 작성
  - [v] 로딩 상태에 따른 UI 처리
  - [] 에러 상태에 따른 UI 처리
  - [v] 빈 목록 상태에 따른 UI 처리
  - [] 성공 상태에 따른 UI 처리
