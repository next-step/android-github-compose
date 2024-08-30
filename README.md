# android-github-compose

🚀 1단계 - GitHub(데이터 레이어)

- [x] NEXTSTEP 조직의 저장소 목록을 가져오는 Client를 구현한다.
- [x] GET https://api.github.com/orgs/next-step/repos
- [x] full_name, description 필드만 가져온다.
- [x] 네트워크 요청으로 저장소 목록을 가져오는 기능은 data 패키지에 구현되어야 한다.
- [x] 힌트 코드를 참고하여 수동 DI를 구현한다. (Hilt와 같은 별도의 DI 라이브러리를 활용하지 않는다)
- [x] 실제로 서버 데이터가 잘 로드되는지 Log로 확인한다.

🚀2단계 - GitHub(UI 레이어)
- [x] NEXTSTEP 조직의 저장소 목록을 선형 리스트로 노출한다.
- [x] Material3의 Theme을 활용하여 Typography와 Color를 부여한다.
- [x] 힌트 코드를 참고하여 ViewModel Factory를 구현한다. (Hilt와 같은 별도의 DI 라이브러리를 활용하지 않는다)
- [x] 저장소 목록을 노출하는 UI와 관련된 기능은 ui 패키지에 구현되어야 한다.
- [x] UI 레이어는 데이터 레이어를 의존하지만, 데이터 레이어는 UI 레이어를 의존해선 안 된다.

🚀 3단계 - GitHub(UI 상태)
- [x] 목록이 로딩되기 전에는 로딩 UI를 노출한다.
- [x] 목록이 빈 경우에는 빈 화면 UI를 노출한다.
- [x] 오류가 발생한 경우 재시도 가능한 스낵바를 노출한다.

* 질문사항들이 좀 되는데 pr에 코멘트로 작성하였습니다. 말을 좀 이상하게 한 부분때문에 내용 이해가 안되실 수도 있을 것 같은 걱정이 드네요 😞