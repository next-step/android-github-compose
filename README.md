# android-github-compose

## Step3 구현 목록
- [x] 로딩 UI 추가
- [x] 빈 화면 UI 추가
- [x] 오류 발생 시 스낵바 노출
- [x] 스낵바 재시도 클릭 시 재연결 시도

## Step2 구현 목록

- [x] 저장소 목록을 보여주는 화면 구현
    - [x] 상단바 추가
    - [x] 저장소 목록 추가
- [x] 저장소 목록 데이터를 관리하는 ViewModel 구현
- [x] 저장소 목록 화면에 데이터 연동
- [x] 저장소 목록 화면 리팩터링
- [x] 피드백 반영

## Step2 진행 중 의식의 흐름
-

## Step1 구현 목록

- [x] retrofit 설정
- [x] 수동 DI 설정
- [x] Github API를 사용하여 full_name, description 가져와서 Log로 표시

## Step1 진행 중 의식의 흐름

- 덜 코틀린스럽지만 기본적으로 추가된 라이브러리만 활용하기 위해서 ConverterFactory 지정 시 CONTENT_TYPE.toMediaType() 대신
  MediaType.get(CONTENT_TYPE)을 사용하였습니다.
