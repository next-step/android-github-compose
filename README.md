# Step 1 - GitHub(데이터 레이어)

## 프로그래밍 요구사항
- NEXTSTEP 조직의 저장소 목록을 가져오는 Client를 구현한다.
  - GET https://api.github.com/orgs/next-step/repos
  - full_name, description 필드만 가져온다.
- 네트워크 요청으로 저장소 목록을 가져오는 기능은 data 패키지에 구현되어야 한다.
- 힌트 코드를 참고하여 수동 DI를 구현한다. (Hilt와 같은 별도의 DI 라이브러리를 활용하지 않는다)
  - 실제로 서버 데이터가 잘 로드되는지 Log로 확인한다.

## 기능 요구사항
-[O] 수동 DI를 구현한다.
-[O] NEXTSTEP 조직의 저장소 목록을 가져오는 Client(Service)를 구현한다.
-[O] 저장소 목록을 가져오는 기능은 data 패키지에 구현한다.
-[O] Log를 통해 서버 데이터가 잘 로드되었는지 확인한다.

# Step 2 - GitHub(UI 레이어)

## 프로그래밍 요구사항
- Material3의 Theme을 활용하여 Typography와 Color를 부여한다.
- 힌트 코드를 참고하여 ViewModel Factory를 구현한다. (Hilt와 같은 별도의 DI 라이브러리를 활용하지 않는다)
- 저장소 목록을 노출하는 UI와 관련된 기능은 ui 패키지에 구현되어야 한다.
  - UI 레이어는 데이터 레이어를 의존하지만, 데이터 레이어는 UI 레이어를 의존해선 안 된다.

## 기능 요구사항
- [O] 저장소 목록을 노출하는 화면을 구현한다.
- [O] 저장소 목록을 가져오는 기능은 ViewModel에서 구현한다.

# Step 3 - GitHub(UI 상태)

## 프로그래밍 요구사항
- 로딩 UI를 노출할 때 CircularProgressIndicator를 활용한다.

## 기능 요구사항
- [] 목록이 로딩되기 전에는 로딩 UI를 노출한다.
- [] 목록이 빈 경우에는 빈 화면 UI를 노출한다.
- [] 오류가 발생한 경우 재시도 가능한 스낵바를 노출한다.
