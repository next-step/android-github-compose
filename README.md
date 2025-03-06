# android-github-compose


## **🚀 2단계 - GitHub(UI 레이어)**

---

### 구현 기능 목록

- NEXTSTEP 조직의 저장소 목록을 선형 리스트로 노출한다.
- Material3의 Theme을 활용하여 Typography와 Color를 부여한다.
- 힌트 코드를 참고하여 ViewModel Factory를 구현한다. (Hilt와 같은 별도의 DI 라이브러리를 활용하지 않는다)
- 저장소 목록을 노출하는 UI와 관련된 기능은 **`ui`** 패키지에 구현되어야 한다.
  - UI 레이어는 데이터 레이어를 의존하지만, 데이터 레이어는 UI 레이어를 의존해선 안 된다.