# android-github-compose

## Step1 구현 목록

- [x] retrofit 설정
- [x] 수동 DI 설정
- [x] Github API를 사용하여 full_name, description 가져와서 Log로 표시

## Step1 진행 중 의식의 흐름

- 덜 코틀린스럽지만 기본적으로 추가된 라이브러리만 활용하기 위해서 ConverterFactory 지정 시 CONTENT_TYPE.toMediaType() 대신
  MediaType.get(CONTENT_TYPE)을 사용하였습니다.
