# android-github-compose

### 🚀 1단계 - GitHub(데이터 레이어) 요구 사항
- NEXTSTEP 조직의 저장소 목록을 가져오는 Client를 구현한다.
  - GET https://api.github.com/orgs/next-step/repos
  - full_name, description 필드만 가져온다.
- 네트워크 요청으로 저장소 목록을 가져오는 기능은 data 패키지에 구현되어야 한다.
- 힌트 코드를 참고하여 수동 DI를 구현한다. (Hilt와 같은 별도의 DI 라이브러리를 활용하지 않는다)
  - 실제로 서버 데이터가 잘 로드되는지 Log로 확인한다.

#### 🚀 1단계 - GitHub(데이터 레이어) 개선 사항
- GithubResponse 클래스 명 api 호출 의도에 맞게 변경
- remote.api.mapper > remote.mapper 패키지 이동
- getRepositories 첫 조회 viewModel init에서 호출하도록 수정
- 화면 기준 패키지 재구성

### 🚀 2단계 - GitHub(UI 레이어) 요구 사항
- NEXTSTEP 조직의 저장소 목록을 선형 리스트로 노출한다
- Material3의 Theme을 활용하여 Typography와 Color를 부여한다.
- 힌트 코드를 참고하여 ViewModel Factory를 구현한다. (Hilt와 같은 별도의 DI 라이브러리를 활용하지 않는다)
- 저장소 목록을 노출하는 UI와 관련된 기능은 ui 패키지에 구현되어야 한다.
- UI 레이어는 데이터 레이어를 의존하지만, 데이터 레이어는 UI 레이어를 의존해선 안 된다.
