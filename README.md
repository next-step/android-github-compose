# android-github-compose

## 1단계 - GitHub(데이터 레이어) 요구사항

- [x] NEXTSTEP 조직의 저장소 목록을 가져오는 Client를 구현한다.
- [x] full_name, description 필드만 가져온다.
- [x] 네트워크 요청으로 저장소 목록을 가져오는 기능은 data 패키지에 구현되어야 한다.
- [x] 힌트 코드를 참고하여 수동 DI를 구현한다. (Hilt와 같은 별도의 DI 라이브러리를 활용하지 않는다)
- [x] 실제로 서버 데이터가 잘 로드되는지 Log로 확인한다.

## 2단계 GitHub(UI 레이어) 요구사항

- [x] NEXTSTEP 조직의 저장소 목록을 선형 리스트로 노출한다.
- [x] Material3의 Theme을 활용하여 Typography와 Color를 부여한다.
- [x] 힌트 코드를 참고하여 ViewModel Factory를 구현한다. (Hilt와 같은 별도의 DI 라이브러리를 활용하지 않는다)
- [x] 저장소 목록을 노출하는 UI와 관련된 기능은 ui 패키지에 구현되어야 한다.
- [x] UI 레이어는 데이터 레이어를 의존하지만, 데이터 레이어는 UI 레이어를 의존해선 안 된다.

## 3단계 GitHub(UI 상태) 요구사항

- [ ] 목록이 로딩되기 전에는 로딩 UI를 노출한다.
- [ ] 목록이 빈 경우에는 빈 화면 UI를 노출한다.
- [ ] 오류가 발생한 경우 재시도 가능한 스낵바를 노출한다.
- [ ] 기능 요구 사항에 대한 UI 테스트를 작성한다.
- [ ] 로딩 UI를 노출할 때 `CircularProgressIndicator`를 활용한다.
