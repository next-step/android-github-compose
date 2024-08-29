# 프로그래밍 요구 사항
- NEXTSTEP 조직의 저장소 목록을 가져오는 Client를 구현한다.
  - GET https://api.github.com/orgs/next-step/repos
  - full_name, description 필드만 가져온다.
- 네트워크 요청으로 저장소 목록을 가져오는 기능은 data 패키지에 구현되어야 한다.
- 힌트 코드를 참고하여 수동 DI를 구현한다. (Hilt와 같은 별도의 DI 라이브러리를 활용하지 않는다)
  - 실제로 서버 데이터가 잘 로드되는지 Log로 확인한다.

# 기능 요구사항
-[] 수동 DI를 구현한다.
-[] NEXTSTEP 조직의 저장소 목록을 가져오는 Client(Service)를 구현한다.
-[] 저장소 목록을 가져오는 기능은 data 패키지에 구현한다.
-[] Log를 통해 서버 데이터가 잘 로드되었는지 확인한다.
