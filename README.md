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

#### 🚀 2단계 - GitHub(UI 레이어) 개선 사항
- GithubScreen(stateless) private 접근 제어자 제거 (테스트 용이성)
- Text style 코드 간소화 (style 지정하면 일괄 적용)
- Color 지정을 MaterialTheme.colors 사용하도록 변경 (확장 프로퍼티 활용)
- LazyColumn key 추가 (성능 최적화)
- viewModelScope Dispatchers.IO 제거(Retrofit 내부적으로 적절히 세팅해줌)
- GithubScreen 내부 컴포넌트 분리
- GithubViewModel getRepositories > fetchRepositories로 함수명 변경(의도 명확화)
- HorizontalDivider thickness 1dp 제거 (내부 기본값이 1dp)

### 🚀 3단계 - GitHub(UI 상태) 요구 사항
- 목록이 로딩되기 전에는 로딩 UI를 노출한다.
- 목록이 빈 경우에는 빈 화면 UI를 노출한다.
- 오류가 발생한 경우 재시도 가능한 스낵바를 노출한다.

